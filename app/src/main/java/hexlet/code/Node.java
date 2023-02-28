package hexlet.code;

public final class Node {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public Node(String diffKey, Object oldVal, Object newVal, String diffStatus) {
        this.key = diffKey;
        this.oldValue = oldVal;
        this.newValue = newVal;
        this.status = diffStatus;
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
