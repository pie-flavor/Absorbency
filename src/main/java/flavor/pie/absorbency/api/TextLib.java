package flavor.pie.absorbency.api;

import flavor.pie.absorbency.Absorbency;
import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.LuaJson;
import flavor.pie.absorbency.util.MetatableBindings;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class TextLib extends TwoArgFunction {

    public static LuaTable instance;

    public static LuaTable text(LuaValue val) {
        if (val.istable()) {
            return (LuaTable) GlobalFunctions.copy(val, true);
        } else {
            LuaTable tbl = tableOf();
            tbl.set("text", val.checkstring());
            return tbl;
        }
    }

    public static LuaTable concat(LuaValue txt1, LuaValue txt2) {
        LuaTable tbl = text(txt1);
        LuaTable list = tbl.get("extra").opttable(tableOf());
        list.set(list.rawlen() + 1, txt2);
        tbl.set("extra", list);
        return tbl;
    }

    public static LuaTable format(LuaValue text, LuaValue format) {
        LuaTable tbl = text(text);
        format.checktable();
        if (!format.get("color").isnil()) {
            tbl.set("color", format.get("color"));
        }
        if (!format.get("bold").isnil()) {
            tbl.set("bold", format.get("bold"));
        }
        if (!format.get("underlined").isnil()) {
            tbl.set("underlined", format.get("underlined"));
        }
        if (!format.get("italic").isnil()) {
            tbl.set("italic", format.get("italic"));
        }
        if (!format.get("strikethrough").isnil()) {
            tbl.set("strikethrough", format.get("strikethrough"));
        }
        if (!format.get("obfuscated").isnil()) {
            tbl.set("obfuscated", format.get("obfuscated"));
        }
        return tbl;
    }

    public static LuaTable getFormat(LuaValue text) {
        LuaTable tbl = tableOf();
        if (!text.get("color").isnil()) {
            tbl.set("color", text.get("color"));
        }
        if (!text.get("bold").isnil()) {
            tbl.set("bold", text.get("bold"));
        }
        if (!text.get("underlined").isnil()) {
            tbl.set("underlined", text.get("underlined"));
        }
        if (!text.get("italic").isnil()) {
            tbl.set("italic", text.get("italic"));
        }
        if (!text.get("strikethrough").isnil()) {
            tbl.set("strikethrough", text.get("strikethrough"));
        }
        if (!text.get("obfuscated").isnil()) {
            tbl.set("obfuscated", text.get("obfuscated"));
        }
        return tbl;
    }

    public static Text toText(LuaValue text) {
        // and this took me a lot of time too
//        text.checktable();
//        Text.Builder builder;
//        if (!text.get("text").isnil()) {
//            builder = Text.builder(text.get("text").checkjstring());
//        } else if (!text.get("translate").isnil()) {
//            Translation translation = Sponge.getRegistry().getTranslationById(text.get("translate").checkjstring()).orElseThrow(() -> new LuaError("Invalid translation ID"));
//            if (!text.get("with").isnil()) {
//                LuaTable with = text.get("with").checktable();
//                if (with.length() > 0) {
//                    List<String> args = new ArrayList<>();
//                    LuaValue key = NIL;
//                    while (true) {
//                        Varargs value = with.next(key);
//                        if ((key = value.arg1()).isnil()) {
//                            break;
//                        }
//                        args.add(value.arg(2).checkjstring());
//                    }
//                    builder = Text.builder(translation, args);
//                } else {
//                    builder = Text.builder(translation);
//                }
//            } else {
//                builder = Text.builder(translation);
//            }
//        } else if (!text.get("score").isnil()) {
//            LuaTable score = text.get("score").checktable();
//            String name = text.get("name").checkjstring();
//            String objective = text.get("objective").checkjstring();
//            if (!text.get("value").isnil()) {
//                builder = TextSerializers.JSON.deserialize(String.format("{\"name\":\"%s\",\"objective\":\"%s\",\"value\":\"%s\"}", name, objective, text.get("value").checkjstring())).toBuilder();
//            } else {
//                builder = TextSerializers.JSON.deserialize(String.format("{\"name\":\"%s\",\"objective\":\"%s\"}", name, objective)).toBuilder();
//            }
//        } else if (!text.get("selector").isnil()) {
//            String selector = text.get("selector").checkjstring();
//            Selector sel = Selector.parse(selector);
//            builder = Text.builder(sel);
//        } else {
//            builder = Text.builder();
//        }
//        if (!text.get("color").isnil()) {
//            builder.color(Sponge.getRegistry().getType(CatalogTypes.TEXT_COLOR, text.get("color").checkjstring()).orElseThrow(() -> new LuaError("Invalid color")));
//        }
//        if (!text.get("bold").isnil() && text.get("bold").checkboolean()) {
//            builder.style(TextStyles.BOLD);
//        }
//        if (!text.get("underlined").isnil() && text.get("underlined").checkboolean()) {
//            builder.style(TextStyles.UNDERLINE);
//        }
//        if (!text.get("italic").isnil() && text.get("italic").checkboolean()) {
//            builder.style(TextStyles.ITALIC);
//        }
//        if (!text.get("strikethrough").isnil() && text.get("strikethrough").checkboolean()) {
//            builder.style(TextStyles.STRIKETHROUGH);
//        }
//        if (!text.get("obfuscated").isnil() && text.get("obfuscated").checkboolean()) {
//            builder.style(TextStyles.OBFUSCATED);
//        }
//        if (!text.get("insertion").isnil()) {
//            builder.onShiftClick(TextActions.insertText(text.get("insertion").checkjstring()));
//        }
//        if (!text.get("clickEvent").isnil()) {
//            LuaTable clickEvent = text.get("clickEvent").checktable();
//            switch (clickEvent.get("action").checkjstring()) {
//                case "open_url":
//                    builder.onClick(TextActions.openUrl(Throw.uncheckedV(() -> new URL(clickEvent.get("value").checkjstring()))));
//                    break;
//                case "run_command":
//                    builder.onClick(TextActions.runCommand(clickEvent.get("value").tojstring()));
//                    break;
//                case "change_page":
//                    builder.onClick(TextActions.changePage(clickEvent.get("value").checkint()));
//                    break;
//                case "suggest_command":
//                    builder.onClick(TextActions.suggestCommand(clickEvent.get("value").checkjstring()));
//                    break;
//                default:
//                    throw new LuaError("Invalid clickEvent");
//            }
//        }
//        if (!text.get("hoverEvent").isnil()) {
//            LuaTable hoverEvent = text.get("hoverEvent").checktable();
//            switch (hoverEvent.get("action").checkjstring()) {
//                case "show_text":
//                    LuaValue value = hoverEvent.get("value");
//                    Text t;
//                    if (value.istable()) {
//                        t = toText(value);
//                    } else if (value.isstring()) {
//                        t = TextSerializers.JSON.deserialize(value.tojstring());
//                    } else {
//                        throw new LuaError("Invalid text");
//                    }
//                    builder.onHover(TextActions.showText(t));
//                case "show_item":
//                    throw new LuaError("show_item not supported yet");
//                case "show_achievement":
//                    builder.onHover(TextActions.showAchievement(Sponge.getRegistry().getType(CatalogTypes.ACHIEVEMENT, hoverEvent.get("value").checkjstring()).orElseThrow(() -> new LuaError("Invalid achievement"))));
//                    break;
//                case "show_entity":
//                    throw new LuaError("show_entity not supported yet");
//                default:
//                    throw new LuaError("Invalid hoverEvent");
//            }
//        }
//        if (!text.get("extra").isnil()) {
//            LuaTable extra = text.get("extra").checktable();
//            LuaValue key = NIL;
//            ArrayList<Text> list = new ArrayList<>();
//            while (true) {
//                Varargs value = extra.next(key);
//                if ((key = value.arg1()).isnil()) {
//                    break;
//                }
//                list.add(toText(value.arg(2)));
//            }
//            builder.append(list);
//        }
//        return builder.build();
        return TextSerializers.JSON.deserialize(LuaJson.toJson(text(text)));
    }

    public static LuaTable fromText(Text text) {
        return MetatableBindings.applyMutable(LuaJson.fromJson(TextSerializers.JSON.serialize(text)), instance);
    }

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        LuaTable mt = tableOf();
        mt.set(INDEX, tbl);
        tbl.set("_mt", mt);
        tbl.set("mt", FunctionHelper.func1V(text -> text.checktable().setmetatable(mt)));
        tbl.set("concat", FunctionHelper.func2(TextLib::concat));
        tbl.set("format", FunctionHelper.func2(TextLib::format));
        env.set("text", tbl);
        if (instance == null) {
            instance = tbl;
        }
        return tbl;
    }

}
