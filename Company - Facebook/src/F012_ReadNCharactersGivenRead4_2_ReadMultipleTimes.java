// LeetCode 158
public class F012_ReadNCharactersGivenRead4_2_ReadMultipleTimes {
    // TC: O(n)
    // SC: O(1)
    private int bufferPtr = 0;
    private int bufferCount = 0;
    private char[] buffer = new char[4];
    public int read(char[] buf, int n) {
        // ptr is a pointer to both the file and the buf array we need to fill in
        int ptr = 0;
        while (ptr < n) {
            // bufferPtr == 0 means we the buffer is empty, so we should read from file
            if (bufferPtr == 0) {
                bufferCount = read4(buffer);
            }
            // bufferCount == 0 means we have reached the end of file, nothing
            // read from previous call
            if (bufferCount == 0) {
                break;
            }
            // fill in the buf array from buffer
            while (ptr < n && bufferPtr < bufferCount) {
                buf[ptr++] = buffer[bufferPtr++];
            }
            // if we used up what we read last time, start a new round
            if (bufferPtr == bufferCount) {
                bufferPtr = 0;
            }
        }
        return ptr;
    }

    // 为了写在ide里不显示出错随便加的method
    public int read4(char[] buf) {
        return 0;
    }
}
