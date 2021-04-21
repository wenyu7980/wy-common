package com.wenyu7980.common.converter.kryo;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.esotericsoftware.kryo.kryo5.util.Pool;

/**
 *
 * @author wenyu
 */
public abstract class KryoConverterUtils {
    private static final Pool<Kryo> POOLS = new Pool<Kryo>(true, false, 8) {
        @Override
        protected Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setClassLoader(KryoConverterUtils.class.getClassLoader());
            kryo.setWarnUnregisteredClasses(true);
            kryo.setRegistrationRequired(false);
            return kryo;
        }
    };

    public static <T> byte[] write(T object) {
        if (object == null) {
            return null;
        }
        Kryo kryo = POOLS.obtain();
        try (Output output = new Output(1024, -1)) {
            kryo.writeClassAndObject(output, object);
            return output.toBytes();
        } finally {
            POOLS.free(kryo);
        }
    }

    public static <T> T read(byte[] bytes, Class<T> clazz) {
        if (bytes == null) {
            return null;
        }
        Kryo kryo = POOLS.obtain();
        try (Input input = new Input(bytes)) {
            return (T) kryo.readClassAndObject(input);
        } finally {
            POOLS.free(kryo);
        }
    }

    public static Object read(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        Kryo kryo = POOLS.obtain();
        try (Input input = new Input(bytes)) {
            return kryo.readClassAndObject(input);
        } finally {
            POOLS.free(kryo);
        }
    }
}
