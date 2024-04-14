//package net.PeytonPlayz585.shadow.reflect;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import net.PeytonPlayz585.shadow.ArrayUtils;
//import net.PeytonPlayz585.shadow.Config;
//import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
//import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.resources.DefaultResourcePack;
//
//public class Reflector {
//    private static final Logger LOGGER = LogManager.getLogger();
//    public static ReflectorClass Minecraft = new ReflectorClass(Minecraft.class);
//    public static ReflectorField Minecraft_defaultResourcePack = new ReflectorField(Minecraft, DefaultResourcePack.class);
//
//    public static void callVoid(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return;
//            }
//
//            method.invoke((Object) null, params);
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//        }
//    }
//
//    public static boolean callBoolean(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return false;
//            } else {
//                Boolean obool = (Boolean) method.invoke((Object) null, params);
//                return obool.booleanValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//            return false;
//        }
//    }
//
//    public static int callInt(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return 0;
//            } else {
//                Integer integer = (Integer) method.invoke((Object) null, params);
//                return integer.intValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//            return 0;
//        }
//    }
//
//    public static float callFloat(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return 0.0F;
//            } else {
//                Float f = (Float) method.invoke((Object) null, params);
//                return f.floatValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//            return 0.0F;
//        }
//    }
//
//    public static double callDouble(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return 0.0D;
//            } else {
//                Double d0 = (Double) method.invoke((Object) null, params);
//                return d0.doubleValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//            return 0.0D;
//        }
//    }
//
//    public static String callString(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return null;
//            } else {
//                String s = (String) method.invoke((Object) null, params);
//                return s;
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//            return null;
//        }
//    }
//
//    public static Object call(ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return null;
//            } else {
//                Object object = method.invoke((Object) null, params);
//                return object;
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, (Object) null, refMethod, params);
//            return null;
//        }
//    }
//
//    public static void callVoid(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            if (obj == null) {
//                return;
//            }
//
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return;
//            }
//
//            method.invoke(obj, params);
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//        }
//    }
//
//    public static boolean callBoolean(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return false;
//            } else {
//                Boolean obool = (Boolean) method.invoke(obj, params);
//                return obool.booleanValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//            return false;
//        }
//    }
//
//    public static int callInt(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return 0;
//            } else {
//                Integer integer = (Integer) method.invoke(obj, params);
//                return integer.intValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//            return 0;
//        }
//    }
//
//    public static float callFloat(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return 0.0F;
//            } else {
//                Float f = (Float) method.invoke(obj, params);
//                return f.floatValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//            return 0.0F;
//        }
//    }
//
//    public static double callDouble(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return 0.0D;
//            } else {
//                Double d0 = (Double) method.invoke(obj, params);
//                return d0.doubleValue();
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//            return 0.0D;
//        }
//    }
//
//    public static String callString(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return null;
//            } else {
//                String s = (String) method.invoke(obj, params);
//                return s;
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//            return null;
//        }
//    }
//
//    public static Object call(Object obj, ReflectorMethod refMethod, Object...params) {
//        try {
//            Method method = refMethod.getTargetMethod();
//
//            if (method == null) {
//                return null;
//            } else {
//                Object object = method.invoke(obj, params);
//                return object;
//            }
//        } catch (Throwable throwable) {
//            handleException(throwable, obj, refMethod, params);
//            return null;
//        }
//    }
//
//    public static Object getFieldValue(ReflectorField refField) {
//        return getFieldValue((Object) null, refField);
//    }
//
//    public static Object getFieldValue(Object obj, ReflectorField refField) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return null;
//            } else {
//                Object object = field.get(obj);
//                return object;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return null;
//        }
//    }
//
//    public static boolean getFieldValueBoolean(ReflectorField refField, boolean def) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return def;
//            } else {
//                boolean flag = field.getBoolean((Object) null);
//                return flag;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return def;
//        }
//    }
//
//    public static boolean getFieldValueBoolean(Object obj, ReflectorField refField, boolean def) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return def;
//            } else {
//                boolean flag = field.getBoolean(obj);
//                return flag;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return def;
//        }
//    }
//
//    public static Object getFieldValue(ReflectorFields refFields, int index) {
//        ReflectorField reflectorfield = refFields.getReflectorField(index);
//        return reflectorfield == null ? null : getFieldValue(reflectorfield);
//    }
//
//    public static Object getFieldValue(Object obj, ReflectorFields refFields, int index) {
//        ReflectorField reflectorfield = refFields.getReflectorField(index);
//        return reflectorfield == null ? null : getFieldValue(obj, reflectorfield);
//    }
//
//    public static float getFieldValueFloat(Object obj, ReflectorField refField, float def) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return def;
//            } else {
//                float f = field.getFloat(obj);
//                return f;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return def;
//        }
//    }
//
//    public static int getFieldValueInt(Object obj, ReflectorField refField, int def) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return def;
//            } else {
//                int i = field.getInt(obj);
//                return i;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return def;
//        }
//    }
//
//    public static long getFieldValueLong(Object obj, ReflectorField refField, long def) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return def;
//            } else {
//                long i = field.getLong(obj);
//                return i;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return def;
//        }
//    }
//
//    public static boolean setFieldValue(ReflectorField refField, Object value) {
//        return setFieldValue((Object) null, refField, value);
//    }
//
//    public static boolean setFieldValue(Object obj, ReflectorField refField, Object value) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return false;
//            } else {
//                field.set(obj, value);
//                return true;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return false;
//        }
//    }
//
//    public static boolean setFieldValueInt(ReflectorField refField, int value) {
//        return setFieldValueInt((Object) null, refField, value);
//    }
//
//    public static boolean setFieldValueInt(Object obj, ReflectorField refField, int value) {
//        try {
//            Field field = refField.getTargetField();
//
//            if (field == null) {
//                return false;
//            } else {
//                field.setInt(obj, value);
//                return true;
//            }
//        } catch (Throwable throwable) {
//            Config.error("", throwable);
//            return false;
//        }
//    }
//
//    public static Object newInstance(ReflectorConstructor constr, Object...params) {
//        Constructor constructor = constr.getTargetConstructor();
//
//        if (constructor == null) {
//            return null;
//        } else {
//            try {
//                Object object = constructor.newInstance(params);
//                return object;
//            } catch (Throwable throwable) {
//                handleException(throwable, constr, params);
//                return null;
//            }
//        }
//    }
//
//    public static boolean matchesTypes(Class[] pTypes, Class[] cTypes) {
//        if (pTypes.length != cTypes.length) {
//            return false;
//        } else {
//            for (int i = 0; i < cTypes.length; ++i) {
//                Class oclass = pTypes[i];
//                Class oclass1 = cTypes[i];
//
//                if (oclass != oclass1) {
//                    return false;
//                }
//            }
//
//            return true;
//        }
//    }
//
//    private static void dbgCall(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params, Object retVal) {
//        String s = refMethod.getTargetMethod().getDeclaringClass().getName();
//        String s1 = refMethod.getTargetMethod().getName();
//        String s2 = "";
//
//        if (isStatic) {
//            s2 = " static";
//        }
//
//        Config.dbg(callType + s2 + " " + s + "." + s1 + "(" + ArrayUtils.arrayToString(params) + ") => " + retVal);
//    }
//
//    private static void dbgCallVoid(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params) {
//        String s = refMethod.getTargetMethod().getDeclaringClass().getName();
//        String s1 = refMethod.getTargetMethod().getName();
//        String s2 = "";
//
//        if (isStatic) {
//            s2 = " static";
//        }
//
//        Config.dbg(callType + s2 + " " + s + "." + s1 + "(" + ArrayUtils.arrayToString(params) + ")");
//    }
//
//    private static void dbgFieldValue(boolean isStatic, String accessType, ReflectorField refField, Object val) {
//        String s = refField.getTargetField().getDeclaringClass().getName();
//        String s1 = refField.getTargetField().getName();
//        String s2 = "";
//
//        if (isStatic) {
//            s2 = " static";
//        }
//
//        Config.dbg(accessType + s2 + " " + s + "." + s1 + " => " + val);
//    }
//
//    private static void handleException(Throwable e, Object obj, ReflectorMethod refMethod, Object[] params) {
//        if (e instanceof InvocationTargetException) {
//            Throwable throwable = e.getCause();
//
//            if (throwable instanceof RuntimeException) {
//                RuntimeException runtimeexception = (RuntimeException) throwable;
//                throw runtimeexception;
//            } else {
//                Config.error("", e);
//            }
//        } else {
//            Config.warn("*** Exception outside of method ***");
//            Config.warn("Method deactivated: " + refMethod.getTargetMethod());
//            refMethod.deactivate();
//
//            if (e instanceof IllegalArgumentException) {
//                Config.warn("*** IllegalArgumentException ***");
//                Config.warn("Method: " + refMethod.getTargetMethod());
//                Config.warn("Object: " + obj);
//                Config.warn("Parameter classes: " + ArrayUtils.arrayToString(getClasses(params)));
//                Config.warn("Parameters: " + ArrayUtils.arrayToString(params));
//            }
//
//            Config.warn("", e);
//        }
//    }
//
//    private static void handleException(Throwable e, ReflectorConstructor refConstr, Object[] params) {
//        if (e instanceof InvocationTargetException) {
//            Config.error("", e);
//        } else {
//            Config.warn("*** Exception outside of constructor ***");
//            Config.warn("Constructor deactivated: " + refConstr.getTargetConstructor());
//            refConstr.deactivate();
//
//            if (e instanceof IllegalArgumentException) {
//                Config.warn("*** IllegalArgumentException ***");
//                Config.warn("Constructor: " + refConstr.getTargetConstructor());
//                Config.warn("Parameter classes: " + ArrayUtils.arrayToString(getClasses(params)));
//                Config.warn("Parameters: " + ArrayUtils.arrayToString(params));
//            }
//
//            Config.warn("", e);
//        }
//    }
//
//    private static Object[] getClasses(Object[] objs) {
//        if (objs == null) {
//            return new Class[0];
//        } else {
//            Class[] aclass = new Class[objs.length];
//
//            for (int i = 0; i < aclass.length; ++i) {
//                Object object = objs[i];
//
//                if (object != null) {
//                    aclass[i] = object.getClass();
//                }
//            }
//
//            return aclass;
//        }
//    }
//
//    private static ReflectorField[] getReflectorFields(ReflectorClass parentClass, Class fieldType, int count) {
//        ReflectorField[] areflectorfield = new ReflectorField[count];
//
//        for (int i = 0; i < areflectorfield.length; ++i) {
//            areflectorfield[i] = new ReflectorField(parentClass, fieldType, i);
//        }
//
//        return areflectorfield;
//    }
//
//    private static boolean logEntry(String str) {
//        LOGGER.info("[OptiFine] " + str);
//        return true;
//    }
//
//    private static boolean registerResolvable(final String str) {
//        IResolvable iresolvable = new IResolvable() {
//            public void resolve() {
//                Reflector.LOGGER.info("[OptiFine] " + str);
//            }
//        };
//        ReflectorResolver.register(iresolvable);
//        return true;
//    }
//}