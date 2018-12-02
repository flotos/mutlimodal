class Shape
{
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	private String name;
    private String color;
    private int x;
    private int y;
    
    public Shape(String name, String color, int x, int y) {
    	this.name = name;
    	this.color = color;
    	this.x = x;
    	this.y = y;
    }
    public Shape(String name, int x, int y) {
    	this.name = name;
    	this.x = x;
    	this.y = y;
    }
 };