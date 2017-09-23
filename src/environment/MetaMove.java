package environment;

/**
 * Created by Aaron Gehri on 9/20/2017.
 */
public class MetaMove {
    private final Move metaBoardMove;
    private final Move smallBoardMove;

    public MetaMove(Move metaBoardMove, Move smallBoardMove) {
        this.metaBoardMove = metaBoardMove;
        this.smallBoardMove = smallBoardMove;
    }

    public Move getMetaBoardMove() {
        return metaBoardMove;
    }

    public Move getSmallBoardMove() {
        return smallBoardMove;
    }

    @Override
    public String toString() {
        return "MetaMove{" +
                "metaBoardMove=" + metaBoardMove +
                ", smallBoardMove=" + smallBoardMove +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaMove metaMove = (MetaMove) o;

        if (metaBoardMove != null ? !metaBoardMove.equals(metaMove.metaBoardMove) : metaMove.metaBoardMove != null)
            return false;
        return smallBoardMove != null ? smallBoardMove.equals(metaMove.smallBoardMove) : metaMove.smallBoardMove == null;
    }

    @Override
    public int hashCode() {
        int result = metaBoardMove != null ? metaBoardMove.hashCode() : 0;
        result = 31 * result + (smallBoardMove != null ? smallBoardMove.hashCode() : 0);
        return result;
    }
}
