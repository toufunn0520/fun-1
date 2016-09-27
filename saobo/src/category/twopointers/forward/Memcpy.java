package category.twopointers.forward;

public class Memcpy {

    /**
     * [Expedia Phone] implement memcpy which copys string from sourcePosition to destination position.
     * 
     * @param buffer
     * @param sourcePosition
     * @param destPosition
     * @param length
     * @throws Exception
     */
    public static void memcpy(int[] buffer, int sourcePosition, int destPosition, int length) throws Exception {
        if (buffer == null) {
            return;
        }

        if (sourcePosition < 0 || destPosition < 0 || sourcePosition + length >= buffer.length
                || destPosition + length >= buffer.length) {
            throw new RuntimeException("The input index range isnot valud");
        }

        if (length > buffer.length || length < 0) {
            throw new RuntimeException("The input length cannot exceed the length of the buffer");
        }

        if (buffer.length == 0 || length == 0 || sourcePosition == destPosition) {
            return;
        }

        if (sourcePosition < destPosition) {
            for (int i = length - 1; i >= 0; i--) {
                buffer[destPosition + i] = buffer[sourcePosition + i];
            }
        } else {
            for (int i = 0; i < length; i++) {
                buffer[destPosition + i] = buffer[sourcePosition + i];
            }
        }
    }
}
