package flavor.pie.absorbency;

import com.google.inject.Inject;
import flavor.pie.absorbency.api.ChatLib;
import flavor.pie.absorbency.api.EntityLib;
import flavor.pie.absorbency.api.EventLib;
import flavor.pie.absorbency.api.GlobalFunctions;
import flavor.pie.absorbency.api.ItemLib;
import flavor.pie.absorbency.api.PlayerLib;
import flavor.pie.absorbency.api.Script;
import flavor.pie.absorbency.api.ScriptLib;
import flavor.pie.absorbency.api.ServerLib;
import flavor.pie.absorbency.api.TextLib;
import flavor.pie.absorbency.util.Throw;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.CoroutineLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseIoLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import org.luaj.vm2.lib.jse.JseOsLib;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameConstructionEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Plugin(id = "absorbency", name = "Absorbency", version = "1.0-SNAPSHOT", description = "Lua scripting for SpongeLib.", authors = "pie_flavor")
public class Absorbency {
    @Inject
    Game game;
    @Inject @DefaultConfig(sharedRoot = true)
    Path path;
    @Inject @DefaultConfig(sharedRoot = true)
    ConfigurationLoader<CommentedConfigurationNode> loader;
    @Inject
    public Logger logger;
    public static Globals _G;
    public Map<String, Script> scripts = new HashMap<>();

    public static Absorbency instance;
    private Config config;

    @Listener
    public void construct(GameConstructionEvent e) throws IOException, ObjectMappingException {
        instance = this;
        _G = new Globals();
        _G.load(new JseBaseLib());
        _G.load(new PackageLib());
        _G.load(new Bit32Lib());
        _G.load(new TableLib());
        _G.load(new StringLib());
        _G.load(new JseOsLib());
        _G.load(new JseMathLib());
        _G.load(new CoroutineLib());
        _G.load(new JseIoLib());
        _G.load(new GlobalFunctions());
        _G.load(new ScriptLib());
        _G.load(new TextLib());
        _G.load(new EventLib());
        _G.load(new ChatLib());
        _G.load(new PlayerLib());
        _G.load(new EntityLib());
        _G.load(new ServerLib());
        _G.load(new ItemLib());
        LoadState.install(_G);
        LuaC.install(_G);
        if (!Files.exists(path)) {
            game.getAssetManager().getAsset(this, "default.conf").get().copyToFile(path);
        }
        config = loader.load().getValue(Config.type);
    }

    private static final Pattern LUA_EXT = Pattern.compile("^(?<filename>.+)\\.lua$");
    @Listener
    public void starting(GameStartingServerEvent e) throws IOException {
        Path scriptPath = Paths.get(config.scriptsDir);
        if (!Files.exists(scriptPath)) {
            Files.createDirectories(scriptPath);
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(scriptPath, "*.lua")) {
            stream.forEach(f -> Throw.unchecked(() -> {
                Matcher matcher = LUA_EXT.matcher(f.getFileName().toString());
                if (matcher.find()) {
                    try (BufferedReader reader = Files.newBufferedReader(f)) {
                        logger.info("Loading script {}", f.getFileName().toString());
                        String name = matcher.group("filename");
                        LuaValue val = _G.load(reader, name);
                        val.call();
                        _G.set(name, val);
                    }
                }
            }));
        }
    }
}
