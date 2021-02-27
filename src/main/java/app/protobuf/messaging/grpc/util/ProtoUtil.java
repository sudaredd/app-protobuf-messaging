package app.protobuf.messaging.grpc.util;

import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ProtoUtil {

    public static byte[]toByteArrayDelimited(MessageLite request) {
        try (var stream = new ByteArrayOutputStream()) {
            request.writeDelimitedTo(stream);
            return stream.toByteArray();
        } catch (IOException e) {
            log.error("Unable to serialize proto: {}", e.getMessage());
        }
        return null;
    }

    public static <T extends Message> T parse(byte[] bytes, Class<T> messageType) {
        Method parserMethod;
        try {
            parserMethod = messageType.getMethod("parser");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to resolve parser method for protobuf message", e);
        }
        Parser<T> messageParser;
        try {
            // noinspection unchecked
            messageParser = (Parser<T>) parserMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to determine parser for protobuf message", e);
        }
        return tryParse(bytes, messageParser);
    }
    public static <T> T tryParse(byte[] bytes, Parser<T> parser) {
        try (var stream = new ByteArrayInputStream(bytes)) {
            return parser.parseDelimitedFrom(stream);
        } catch (IOException e) {
            log.error("unable to parse proto: {}", e.getMessage());
        }
        return null;
    }
}
