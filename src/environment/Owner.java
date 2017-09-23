package environment;

/**
 * Created by Aaron Gehri on 9/20/2017.
 */
public enum Owner {
    Player1('1'), Player2('2'), Empty(' ');

    private final char name;

    Owner(char name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
