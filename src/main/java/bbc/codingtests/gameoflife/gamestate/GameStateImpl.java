package bbc.codingtests.gameoflife.gamestate;

public class GameStateImpl implements GameState {

    //TODO implement this method such that live cells are represented as a '*' and dead cells are represented by a '.'
    //TODO use newline ('\n') to separate rows
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Please implement the 'toString' method");
    }

    //TODO implement this constructor to parse an input string and return a new GameStateImpl object representing what you got in the string
    //TODO as above, live cells are '*' and dead cells are '.' Rows are separated by newline ('\n')
    public GameStateImpl(String input) {
        throw new UnsupportedOperationException("Please implement the 'GameStateImpl' constructor");
    }

    //TODO implement this method according to explanation in GameState.java
    public boolean isCellAliveAt(int row, int col) {
        throw new UnsupportedOperationException("Please implement the 'isCellAliveAt' method");
    }

    //TODO complete this method according to the requirements
    //TODO two
    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        if(other == null) return false;
        if(!(other instanceof GameStateImpl)) return false;
        throw new UnsupportedOperationException("Please implement the 'equals' method");
    }

    @Override
    public int getRows() {
        throw new UnsupportedOperationException("Please implement the 'getRows' method");
    }

    @Override
    public int getCols() {
        throw new UnsupportedOperationException("Please implement the 'getCols' method");
    }
}
