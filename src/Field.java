public  class Field {
    String name;
    public Field() {
        this("no name for now");
    }
    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("debug#2");
        return name;
    }
}
