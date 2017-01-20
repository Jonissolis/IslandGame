package items;

public class ToolComponent {
	private boolean woodcuttingTool; // Boolean that tells whether this tool can be used for woodcutting or not. 
	
	public boolean canCutWood() {
		return woodcuttingTool;
	}
	
	public void setIsWoodcuttingTool(boolean isWoodcuttingTool) {
		woodcuttingTool = isWoodcuttingTool;
	}
	
}
