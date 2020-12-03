package cn.panghu.blog.common.data;

import cn.panghu.blog.common.constant.GlobalConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import cn.panghu.blog.base.pojo.User;

/**
 * The class Thread local map.
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalMap {
	
	/**
	 * The constant threadContext.
	 */
	private final static ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

	/**
	 * Put.
	 *
	 * @param key   the key
	 * @param value the value
	 */
	public static void put(String key, Object value) {
		getContextMap().put(key, value);
	}

	/**
	 * Remove object.
	 *
	 * @param key the key
	 *
	 * @return the object
	 */
	public static Object remove(String key) {
		return getContextMap().remove(key);
	}

	/**
	 * Get object.
	 *
	 * @param key the key
	 *
	 * @return the object
	 */
	public static Object get(String key) {
		return getContextMap().get(key);
	}
	
	public static Integer getUserId() {
		Object obj = getContextMap().get(GlobalConstant.SESSION_USER_KEY);
		if(obj != null){
			User uToken = (User)obj;
			return uToken.getId();
		}
		return null;
	}
	
	public static Integer getUserType() {
		Object obj = getContextMap().get(GlobalConstant.SESSION_USER_KEY);
		if(obj != null){
			User uToken = (User)obj;
			return uToken.getRoleId();
		}
		return null;
	}
	
	public static String getUserName() {
		Object obj = getContextMap().get(GlobalConstant.SESSION_USER_KEY);
		if(obj != null){
			User uToken = (User)obj;
			return uToken.getUsername();
		}
		return null;
	}

	private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
		/**
		 * Initial value map.
		 *
		 * @return the map
		 */
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>(GlobalConstant.Number.NUM_3) {

				private static final long serialVersionUID = 3637958959138295593L;

				@Override
				public Object put(String key, Object value) {
					return super.put(key, value);
				}
			};
		}
	}

	/**
	 * 取得thread context Map的实例。
	 *
	 * @return thread context Map的实例
	 */
	private static Map<String, Object> getContextMap() {
		return THREAD_CONTEXT.get();
	}

	/**
	 * 清理线程所有被hold住的对象。以便重用！
	 */
	public static void remove() {
		getContextMap().clear();
	}
}