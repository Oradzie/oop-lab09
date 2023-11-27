package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    void setNextPrintString(String str);

    String getNextPrintString();

    List<String> getPrintHistory();

    void printCurrentString();
}
