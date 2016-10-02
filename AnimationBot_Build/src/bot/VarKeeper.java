package bot;

import fileUtil.FileJSONPraser;
import urlUtil.TwitchImageJSON;

public class VarKeeper {
	static boolean waitingProcess = false;
	static String processThis = "";
	static String processKey = "";
	static String processRoot = null;
	static String code = null;
	static int id = -1;
	private static TwitchImageJSON tJSON = new TwitchImageJSON();
	private static FileJSONPraser fJSON = new FileJSONPraser("local.json");
	private static FileJSONPraser configs = new FileJSONPraser("config.json");

	VarKeeper() {

	}
	public static FileJSONPraser getConfigs() {
		return configs;
	}
	public static void setConfigs(FileJSONPraser configs) {
		VarKeeper.configs = configs;
	}
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		VarKeeper.id = id;
	}

	public static boolean isWaitingProcess() {
		return waitingProcess;
	}

	public static void setWaitingProcess(boolean waitingProcess) {
		VarKeeper.waitingProcess = waitingProcess;
	}

	public static String getProcessKey() {
		return processKey;
	}

	public static void setProcessKey(String processKey) {
		VarKeeper.processKey = processKey;
	}

	public static TwitchImageJSON gettJSON() {
		return tJSON;
	}

	public static void settJSON(TwitchImageJSON tJSON) {
		VarKeeper.tJSON = tJSON;
	}

	public static String getProcessRoot() {
		return processRoot;
	}

	public static void setProcessRoot(String processRoot) {
		VarKeeper.processRoot = processRoot;
	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		VarKeeper.code = code;
	}

	public static FileJSONPraser getfJSON() {
		return fJSON;
	}

	public static void setfJSON(FileJSONPraser fJSON) {
		VarKeeper.fJSON = fJSON;
	}

	public static String getProcessThis() {
		return processThis;
	}

	public static void setProcessThis(String processThis) {
		VarKeeper.processThis = processThis;
	}
}
