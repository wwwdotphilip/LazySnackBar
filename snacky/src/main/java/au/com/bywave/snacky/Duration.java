package au.com.bywave.snacky;

public enum Duration {
    SHORT, LONG, INFINITE;

    public static int getDuration(Duration duration){
        switch (duration){
            case SHORT:
                return 3000;
            case LONG:
                return 10000;
            case INFINITE:
                return 0;
        }

        return 3000;
    }
}
