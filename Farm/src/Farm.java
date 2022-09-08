public class Farm {
	public int state = 0; // 作物的状态 0表示未播种；1表示播种；2表示生长；3表示开花；4表示结果

	/**
	 * 播种
	 * 
	 * @param picture
	 */
	public String seed(Crop crop, String picture) {
		String returnValue = ""; // 返回值变量
		if (state == 0) { // 判断作物的状态是否为未播种
			crop.setIcon(picture); // 播种作物
			state = 1; // 设置状态属性为播种
		} else {
			returnValue = getMessage() + "，不能播种！"; // 设置提示信息
		}
		return returnValue;
	}

	/**
	 * 生长
	 * 
	 * @param crop
	 * @param picture
	 */
	public String grow(Crop crop, String picture) {
		String returnValue = ""; // 返回值变量
		if (state == 1) { // 判断作物的状态是否为播种
			crop.setIcon(picture); // 设置作物为生长状态
			state = 2; // 设置状态属性为生长
		} else {
			returnValue = getMessage() + "，不能生长！"; // 设置提示信息
		}
		return returnValue;
	}

	/**
	 * 开花
	 * 
	 * @param crop
	 * @param picture
	 */
	public String bloom(Crop crop, String picture) {
		String returnValue = ""; // 返回值变量
		if (state == 2) { // 判断作物的状态是否为生长
			crop.setIcon(picture); // 设置作物为开花状态
			state = 3; // 设置状态属性为开花
		} else {
			returnValue = getMessage() + "，不能开花！"; // 设置提示信息
		}
		return returnValue;
	}

	/**
	 * 结果
	 * 
	 * @param crop
	 * @param picture
	 */
	public String fruit(Crop crop, String picture) {
		String returnValue = ""; // 返回值变量
		if (state == 3) { // 判断作物的状态是否为开花
			crop.setIcon(picture); // 设置作物为结果状态
			state = 4; // 设置状态属性为结果
		} else {
			returnValue = getMessage() + "，不能结果！"; // 设置提示信息
		}
		return returnValue;
	}

	/**
	 * 收获
	 * 
	 * @param crop
	 * @param picture
	 */
	public String harvest(Crop crop, String picture) {
		String returnValue = ""; // 返回值变量
		if (state == 4) { // 判断作物的状态是否为结果
			crop.setIcon(picture); // 设置作物为未播种状态
			state = 0; // 设置状态属性为未播种
		} else {
			returnValue = getMessage() + "，不能收获！"; // 设置提示信息
		}
		return returnValue;
	}

	/**
	 * 根据作物的状态属性，确定对应的提示信息
	 * 
	 * @return
	 */
	public String getMessage() {
		String message = "";
		switch (state) {
		case 0:
			message = "作物还没有播种";
			break;
		case 1:
			message = "作物刚刚播种";
			break;
		case 2:
			message = "作物正在生长";
			break;
		case 3:
			message = "作物正处于开花期";
			break;
		case 4:
			message = "作物已经结果";
			break;
		}
		return message;
	}
}
