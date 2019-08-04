package hf.common.repository;

/**
 * Created by Sumit Choudhary on 6/16/2019.
 */
public class DataNotFoundInRepoExecption extends Exception {
    public DataNotFoundInRepoExecption() {
        super("Element not found in repository");
    }

    public DataNotFoundInRepoExecption(String message) {
        super(message);
    }
}
