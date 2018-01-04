package wishes.controler.model;

public final class Constants {
	
	public static enum LogLevel {
		NONE,
		DEBUG,
		INFO,
		ERROR
	}
	
	public static LogLevel loglevel = LogLevel.INFO;
	
	public static enum ErrorCode {
		UNKNOWN,
		WISH_SERVICES,
		PERSON_SERVICES,
		ATTACHMENT_SERVICES
	}
	
	public static String errorCodeName (ErrorCode code) {
		
		String codeName = "";
		switch (code) {
		case WISH_SERVICES:
			codeName = "WISH_SERVICES";
			break;
		case PERSON_SERVICES:
			codeName = "PERSON_SERVICES";
			break;
		case ATTACHMENT_SERVICES:
			codeName = "ATTACHMENT_SERVICES";
			break;
		default:
			codeName = "UNKNOWN";
		}
		return codeName;
	}
	
	public static enum ErrorType {
		UNKNOWN,
	    PERSON_NOT_FOUND,
		WISH_NOT_FOUND,
		ATTACHMENT_NOT_FOUND,
		ZERO_PERSONS,
		INCORRECT_PASSWORD,
		BAD_DATE_FORMAT,
		EXISTENT_DATA,
		PERSON_DISABLED
	}
	
	public static String errorTypeName (ErrorType type) {
		
		String typeName = "";
		switch (type) {
		case PERSON_NOT_FOUND:
			typeName = "PERSON_NOT_FOUND";
			break;
		case WISH_NOT_FOUND:
			typeName = "WISH_NOT_FOUND";
			break;
		case ZERO_PERSONS:
			typeName = "ZERO_PERSONS";
			break;
		case INCORRECT_PASSWORD:
			typeName = "INCORRECT_PASSWORD";
			break;
		case BAD_DATE_FORMAT:
			typeName = "BAD_DATE_FORMAT";
			break;
		case EXISTENT_DATA:
			typeName = "EXISTENT_DATA";
			break;
		case PERSON_DISABLED:
			typeName = "PERSON_DISABLED";
			break;
		case ATTACHMENT_NOT_FOUND:
			typeName = "ATTACHMENT_NOT_FOUND";
			break;
		default:
			typeName = "UNKNOWN";
		}
		return typeName;
	}
	
	public static enum WishStatus {
		FREE,
		LOCKED,
		BOUGHT
	}
	
	public static String paymentStatusName (WishStatus status) {
		
		String statusName = "";
		switch (status) {
		case FREE:
			statusName = "FREE";
			break;
		case LOCKED:
			statusName = "LOCKED";
			break;
		case BOUGHT:
			statusName = "BOUGHT";
			break;
		default:
			statusName = "UNKNOWN";
		}
		return statusName;
	}
	
	public static WishStatus intToStatus (int status) {
		switch (status) {
			case 1: return WishStatus.LOCKED;
			case 2: return WishStatus.BOUGHT;
			default: return WishStatus.FREE;
		}
	}
	
	public static enum RoleType {
		ADMINISTRATOR,
		COMMON_USER,
		ADVANCED_USER,
		GUEST
	}
	
	public static String roleTypeName (RoleType role) {
		
		String roleTypeName = "";
		switch (role) {
		case ADMINISTRATOR:
			roleTypeName = "ADMINISTRATOR";
			break;
		case COMMON_USER:
			roleTypeName = "COMMON_USER";
			break;
		case ADVANCED_USER:
			roleTypeName = "ADVANCED_USER";
			break;
		case GUEST:
			roleTypeName = "GUEST";
			break;
		default:
			roleTypeName = "UNKNOWN";
		}
		return roleTypeName;
	}
	
	public static RoleType intToRoleType (int role) {
		switch (role) {
			case 1: return RoleType.ADMINISTRATOR;
			case 2: return RoleType.COMMON_USER;
			case 3: return RoleType.ADVANCED_USER;
			default: return RoleType.GUEST;
		}
	}
}
