package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currentString;
    private final List<String> printHistory = new LinkedList<>();

    @Override
    public void setNextPrintString(String str) {
        this.currentString = Objects.requireNonNull(str, "Passed string must be not null");
    }

    @Override
    public String getNextPrintString() {
        return this.currentString;
    }

    @Override
    public List<String> getPrintHistory() {
        return new LinkedList<String>(this.printHistory);
    }

    @Override
    public void printCurrentString() {
        if (this.currentString.isBlank()) {
            throw new IllegalStateException("The string is unset");
        }
        System.out.println(this.currentString);
        this.printHistory.add(this.currentString);
    }
}
