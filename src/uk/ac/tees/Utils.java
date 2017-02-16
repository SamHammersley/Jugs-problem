package uk.ac.tees;

public final class Utils {
	
    public static final int[] transfer(int srcVolume, int dstVolume, int dstCapacity) {
        if (srcVolume == 0 || dstVolume == dstCapacity) {
            return new int[]{srcVolume, dstVolume};
        }
        
        int[] values = new int[2];
        int total = srcVolume + dstVolume;
        
        if (total <= dstCapacity) {
            values[0] = 0;
            values[1] = total;
        } else {
            values[0] = total - dstCapacity;
            values[1] = dstCapacity;
        }
        
        return values;
    }

    private Utils() {
    }
}