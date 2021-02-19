package com.wenyu7980.common.converter.kryo;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.ByteBufferInputStream;
import com.esotericsoftware.kryo.kryo5.io.ByteBufferOutputStream;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.esotericsoftware.kryo.kryo5.util.Pool;

import java.nio.ByteBuffer;

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

    public static <T> ByteBuffer write(T object) {
        Kryo kryo = POOLS.obtain();
        ByteBufferOutputStream outputStream = new ByteBufferOutputStream();
        try (Output output = new Output(outputStream)) {
            kryo.writeClassAndObject(output, object);
        } finally {
            POOLS.free(kryo);
        }
        return outputStream.getByteBuffer();
    }

    public static <T> T read(ByteBuffer byteBuffer, Class<T> clazz) {
        Kryo kryo = POOLS.obtain();
        ByteBufferInputStream inputStream = new ByteBufferInputStream(byteBuffer);
        try (Input input = new Input(inputStream)) {
            return kryo.readObjectOrNull(input, clazz);
        } finally {
            POOLS.free(kryo);
        }
    }
}
