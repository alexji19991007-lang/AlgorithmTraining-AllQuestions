public abstract class Entry {
    protected Directory parent;
    protected long createdTime;
    protected long lastUpdatedTime;
    protected long lastAccessedTime;
    protected String name;

    public Entry(String n, Directory p) {
        name = n;
        parent = p;
        createdTime = System.currentTimeMillis();
    }

    public boolean delete() {
        if (parent == null) {
            return false;
        }
        return parent.deleteEntry(this);
    }

    public abstract int size();

    public String getFullPath() {
        if (parent == null) {
            return name;
        }
        return parent.getFullPath() + "/" + name;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
