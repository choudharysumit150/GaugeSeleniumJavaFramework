package hf.common.action;

/**
 * Created by Sumit Choudhary on 6/16/2019.
 */
public interface IInputAction {

    boolean setInput(String name, String value);
    boolean setInputAutoPopulate(String name, String value);
    boolean performAction(String name, String action);

}
