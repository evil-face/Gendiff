package hexlet.code;

public class Node {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public Node(String key, Object oldValue, Object newValue, String status) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getStatus() {
        return status;
    }
}
