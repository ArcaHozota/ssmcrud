package jp.co.toshiba.ppok.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 共通ストリング判断ツール
 *
 * @author ArcaHozota
 * @since 5.52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

	/**
	 * 全角半角変換マップ
	 */
	private static final BiMap<String, String> HALF_FULL_CONVERTAR = HashBiMap.create(200);

	/**
	 * UTF-8キャラセット
	 */
	public static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

	/**
	 * 空のストリング
	 */
	public static final String EMPTY_STRING = "";

	static {
		StringUtils.HALF_FULL_CONVERTAR.put("！", "!");
		StringUtils.HALF_FULL_CONVERTAR.put("”", "\"");
		StringUtils.HALF_FULL_CONVERTAR.put("＃", "#");
		StringUtils.HALF_FULL_CONVERTAR.put("＄", "$");
		StringUtils.HALF_FULL_CONVERTAR.put("％", "%");
		StringUtils.HALF_FULL_CONVERTAR.put("＆", "&");
		StringUtils.HALF_FULL_CONVERTAR.put("’", "'");
		StringUtils.HALF_FULL_CONVERTAR.put("（", "(");
		StringUtils.HALF_FULL_CONVERTAR.put("）", ")");
		StringUtils.HALF_FULL_CONVERTAR.put("＊", "*");
		StringUtils.HALF_FULL_CONVERTAR.put("＋", "+");
		StringUtils.HALF_FULL_CONVERTAR.put("，", ",");
		StringUtils.HALF_FULL_CONVERTAR.put("－", "-");
		StringUtils.HALF_FULL_CONVERTAR.put("．", ".");
		StringUtils.HALF_FULL_CONVERTAR.put("／", "/");
		StringUtils.HALF_FULL_CONVERTAR.put("０", "0");
		StringUtils.HALF_FULL_CONVERTAR.put("１", "1");
		StringUtils.HALF_FULL_CONVERTAR.put("２", "2");
		StringUtils.HALF_FULL_CONVERTAR.put("３", "3");
		StringUtils.HALF_FULL_CONVERTAR.put("４", "4");
		StringUtils.HALF_FULL_CONVERTAR.put("５", "5");
		StringUtils.HALF_FULL_CONVERTAR.put("６", "6");
		StringUtils.HALF_FULL_CONVERTAR.put("７", "7");
		StringUtils.HALF_FULL_CONVERTAR.put("８", "8");
		StringUtils.HALF_FULL_CONVERTAR.put("９", "9");
		StringUtils.HALF_FULL_CONVERTAR.put("：", ":");
		StringUtils.HALF_FULL_CONVERTAR.put("；", ";");
		StringUtils.HALF_FULL_CONVERTAR.put("＜", "<");
		StringUtils.HALF_FULL_CONVERTAR.put("＝", "=");
		StringUtils.HALF_FULL_CONVERTAR.put("＞", ">");
		StringUtils.HALF_FULL_CONVERTAR.put("？", "?");
		StringUtils.HALF_FULL_CONVERTAR.put("＠", "@");
		StringUtils.HALF_FULL_CONVERTAR.put("Ａ", "A");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｂ", "B");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｃ", "C");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｄ", "D");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｅ", "E");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｆ", "F");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｇ", "G");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｈ", "H");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｉ", "I");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｊ", "J");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｋ", "K");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｌ", "L");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｍ", "M");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｎ", "N");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｏ", "O");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｐ", "P");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｑ", "Q");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｒ", "R");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｓ", "S");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｔ", "T");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｕ", "U");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｖ", "V");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｗ", "W");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｘ", "X");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｙ", "Y");
		StringUtils.HALF_FULL_CONVERTAR.put("Ｚ", "Z");
		StringUtils.HALF_FULL_CONVERTAR.put("［", "[");
		StringUtils.HALF_FULL_CONVERTAR.put("￥", "\\");
		StringUtils.HALF_FULL_CONVERTAR.put("］", "]");
		StringUtils.HALF_FULL_CONVERTAR.put("＾", "^");
		StringUtils.HALF_FULL_CONVERTAR.put("＿", "_");
		StringUtils.HALF_FULL_CONVERTAR.put("｀", "`");
		StringUtils.HALF_FULL_CONVERTAR.put("ａ", "a");
		StringUtils.HALF_FULL_CONVERTAR.put("ｂ", "b");
		StringUtils.HALF_FULL_CONVERTAR.put("ｃ", "c");
		StringUtils.HALF_FULL_CONVERTAR.put("ｄ", "d");
		StringUtils.HALF_FULL_CONVERTAR.put("ｅ", "e");
		StringUtils.HALF_FULL_CONVERTAR.put("ｆ", "f");
		StringUtils.HALF_FULL_CONVERTAR.put("ｇ", "g");
		StringUtils.HALF_FULL_CONVERTAR.put("ｈ", "h");
		StringUtils.HALF_FULL_CONVERTAR.put("ｉ", "i");
		StringUtils.HALF_FULL_CONVERTAR.put("ｊ", "j");
		StringUtils.HALF_FULL_CONVERTAR.put("ｋ", "k");
		StringUtils.HALF_FULL_CONVERTAR.put("ｌ", "l");
		StringUtils.HALF_FULL_CONVERTAR.put("ｍ", "m");
		StringUtils.HALF_FULL_CONVERTAR.put("ｎ", "n");
		StringUtils.HALF_FULL_CONVERTAR.put("ｏ", "o");
		StringUtils.HALF_FULL_CONVERTAR.put("ｐ", "p");
		StringUtils.HALF_FULL_CONVERTAR.put("ｑ", "q");
		StringUtils.HALF_FULL_CONVERTAR.put("ｒ", "r");
		StringUtils.HALF_FULL_CONVERTAR.put("ｓ", "s");
		StringUtils.HALF_FULL_CONVERTAR.put("ｔ", "t");
		StringUtils.HALF_FULL_CONVERTAR.put("ｕ", "u");
		StringUtils.HALF_FULL_CONVERTAR.put("ｖ", "v");
		StringUtils.HALF_FULL_CONVERTAR.put("ｗ", "w");
		StringUtils.HALF_FULL_CONVERTAR.put("ｘ", "x");
		StringUtils.HALF_FULL_CONVERTAR.put("ｙ", "y");
		StringUtils.HALF_FULL_CONVERTAR.put("ｚ", "z");
		StringUtils.HALF_FULL_CONVERTAR.put("｛", "{");
		StringUtils.HALF_FULL_CONVERTAR.put("｜", "|");
		StringUtils.HALF_FULL_CONVERTAR.put("｝", "}");
		StringUtils.HALF_FULL_CONVERTAR.put("\uff5e", "~");
		StringUtils.HALF_FULL_CONVERTAR.put("。", "｡");
		StringUtils.HALF_FULL_CONVERTAR.put("「", "｢");
		StringUtils.HALF_FULL_CONVERTAR.put("」", "｣");
		StringUtils.HALF_FULL_CONVERTAR.put("、", "､");
		StringUtils.HALF_FULL_CONVERTAR.put("・", "･");
		StringUtils.HALF_FULL_CONVERTAR.put("ァ", "ｧ");
		StringUtils.HALF_FULL_CONVERTAR.put("ィ", "ｨ");
		StringUtils.HALF_FULL_CONVERTAR.put("ゥ", "ｩ");
		StringUtils.HALF_FULL_CONVERTAR.put("ェ", "ｪ");
		StringUtils.HALF_FULL_CONVERTAR.put("ォ", "ｫ");
		StringUtils.HALF_FULL_CONVERTAR.put("ャ", "ｬ");
		StringUtils.HALF_FULL_CONVERTAR.put("ュ", "ｭ");
		StringUtils.HALF_FULL_CONVERTAR.put("ョ", "ｮ");
		StringUtils.HALF_FULL_CONVERTAR.put("ッ", "ｯ");
		StringUtils.HALF_FULL_CONVERTAR.put("ー", "ｰ");
		StringUtils.HALF_FULL_CONVERTAR.put("ア", "ｱ");
		StringUtils.HALF_FULL_CONVERTAR.put("イ", "ｲ");
		StringUtils.HALF_FULL_CONVERTAR.put("ウ", "ｳ");
		StringUtils.HALF_FULL_CONVERTAR.put("エ", "ｴ");
		StringUtils.HALF_FULL_CONVERTAR.put("オ", "ｵ");
		StringUtils.HALF_FULL_CONVERTAR.put("カ", "ｶ");
		StringUtils.HALF_FULL_CONVERTAR.put("キ", "ｷ");
		StringUtils.HALF_FULL_CONVERTAR.put("ク", "ｸ");
		StringUtils.HALF_FULL_CONVERTAR.put("ケ", "ｹ");
		StringUtils.HALF_FULL_CONVERTAR.put("コ", "ｺ");
		StringUtils.HALF_FULL_CONVERTAR.put("サ", "ｻ");
		StringUtils.HALF_FULL_CONVERTAR.put("シ", "ｼ");
		StringUtils.HALF_FULL_CONVERTAR.put("ス", "ｽ");
		StringUtils.HALF_FULL_CONVERTAR.put("セ", "ｾ");
		StringUtils.HALF_FULL_CONVERTAR.put("ソ", "ｿ");
		StringUtils.HALF_FULL_CONVERTAR.put("タ", "ﾀ");
		StringUtils.HALF_FULL_CONVERTAR.put("チ", "ﾁ");
		StringUtils.HALF_FULL_CONVERTAR.put("ツ", "ﾂ");
		StringUtils.HALF_FULL_CONVERTAR.put("テ", "ﾃ");
		StringUtils.HALF_FULL_CONVERTAR.put("ト", "ﾄ");
		StringUtils.HALF_FULL_CONVERTAR.put("ナ", "ﾅ");
		StringUtils.HALF_FULL_CONVERTAR.put("ニ", "ﾆ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヌ", "ﾇ");
		StringUtils.HALF_FULL_CONVERTAR.put("ネ", "ﾈ");
		StringUtils.HALF_FULL_CONVERTAR.put("ノ", "ﾉ");
		StringUtils.HALF_FULL_CONVERTAR.put("ハ", "ﾊ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヒ", "ﾋ");
		StringUtils.HALF_FULL_CONVERTAR.put("フ", "ﾌ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヘ", "ﾍ");
		StringUtils.HALF_FULL_CONVERTAR.put("ホ", "ﾎ");
		StringUtils.HALF_FULL_CONVERTAR.put("マ", "ﾏ");
		StringUtils.HALF_FULL_CONVERTAR.put("ミ", "ﾐ");
		StringUtils.HALF_FULL_CONVERTAR.put("ム", "ﾑ");
		StringUtils.HALF_FULL_CONVERTAR.put("メ", "ﾒ");
		StringUtils.HALF_FULL_CONVERTAR.put("モ", "ﾓ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヤ", "ﾔ");
		StringUtils.HALF_FULL_CONVERTAR.put("ユ", "ﾕ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヨ", "ﾖ");
		StringUtils.HALF_FULL_CONVERTAR.put("ラ", "ﾗ");
		StringUtils.HALF_FULL_CONVERTAR.put("リ", "ﾘ");
		StringUtils.HALF_FULL_CONVERTAR.put("ル", "ﾙ");
		StringUtils.HALF_FULL_CONVERTAR.put("レ", "ﾚ");
		StringUtils.HALF_FULL_CONVERTAR.put("ロ", "ﾛ");
		StringUtils.HALF_FULL_CONVERTAR.put("ワ", "ﾜ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヲ", "ｦ");
		StringUtils.HALF_FULL_CONVERTAR.put("ン", "ﾝ");
		StringUtils.HALF_FULL_CONVERTAR.put("ガ", "ｶﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ギ", "ｷﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("グ", "ｸﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ゲ", "ｹﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ゴ", "ｺﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ザ", "ｻﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ジ", "ｼﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ズ", "ｽﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ゼ", "ｾﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ゾ", "ｿﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ダ", "ﾀﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヂ", "ﾁﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヅ", "ﾂﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("デ", "ﾃﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ド", "ﾄﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("バ", "ﾊﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ビ", "ﾋﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ブ", "ﾌﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ベ", "ﾍﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("ボ", "ﾎﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("パ", "ﾊﾟ");
		StringUtils.HALF_FULL_CONVERTAR.put("ピ", "ﾋﾟ");
		StringUtils.HALF_FULL_CONVERTAR.put("プ", "ﾌﾟ");
		StringUtils.HALF_FULL_CONVERTAR.put("ペ", "ﾍﾟ");
		StringUtils.HALF_FULL_CONVERTAR.put("ポ", "ﾎﾟ");
		StringUtils.HALF_FULL_CONVERTAR.put("ヴ", "ｳﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("\u30f7", "ﾜﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("\u30fa", "ｦﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("゛", "ﾞ");
		StringUtils.HALF_FULL_CONVERTAR.put("゜", "ﾟ");
		StringUtils.HALF_FULL_CONVERTAR.put("\u3000", " ");
	}

	/**
	 * 当ストリングは空かどうかを判断する
	 *
	 * @param str ストリング
	 * @return true: 空, false: 空ではない
	 */
	public static boolean isEmpty(@Nullable final String str) {
		return (str == null) || (str.length() == 0) || str.isBlank();
	}

	/**
	 * 二つのストリングはイコールすることを判断する
	 *
	 * @param str1 ストリング1
	 * @param str2 ストリング2
	 * @return true: イコール, false: イコールしない
	 */
	public static boolean isEqual(@Nullable final String str1, @Nullable final String str2) {
		if ((str1 == null) && (str2 == null)) {
			return true;
		}
		if ((str1 == null) || (str2 == null) || (str1.length() != str2.length())) {
			return false;
		}
		return str1.trim().equals(str2.trim());
	}

	/**
	 * 当ストリングは空ではないかどうかを判断する
	 *
	 * @param str ストリング
	 * @return true: 空ではない, false: 空
	 */
	public static boolean isNotEmpty(@Nullable final String str) {
		return !StringUtils.isEmpty(str);
	}

	/**
	 * 二つのストリングはイコールしないことを判断する
	 *
	 * @param str1 ストリング1
	 * @param str2 ストリング2
	 * @return true: イコールしない, false: イコール
	 */
	public static boolean isNotEqual(@Nullable final String str1, @Nullable final String str2) {
		return !StringUtils.isEqual(str1, str2);
	}

	/**
	 * 全角から半角へ変換
	 *
	 * @param zenkaku 全角文字
	 * @return 半角文字
	 */
	public static String toHankaku(@Nullable final String zenkaku) {
		if (StringUtils.isEmpty(zenkaku)) {
			return StringUtils.EMPTY_STRING;
		}
		final StringBuilder builder = new StringBuilder();
		final List<String> zenkakuList = new ArrayList<>(StringUtils.HALF_FULL_CONVERTAR.keySet());
		for (int i = 0; i < zenkaku.length(); i++) {
			final String charAtString = String.valueOf(zenkaku.charAt(i));
			if (zenkakuList.contains(charAtString)) {
				builder.append(StringUtils.HALF_FULL_CONVERTAR.get(charAtString));
			} else {
				builder.append(charAtString);
			}
		}
		return builder.toString();
	}

	/**
	 * 半角から全角へ変換
	 *
	 * @param hankaku 半角文字
	 * @return 全角文字
	 */
	public static String toZenkaku(@Nullable final String hankaku) {
		if (StringUtils.isEmpty(hankaku)) {
			return StringUtils.EMPTY_STRING;
		}
		final StringBuilder builder = new StringBuilder();
		final List<String> hankakuList = new ArrayList<>(StringUtils.HALF_FULL_CONVERTAR.values());
		for (int i = 0; i < hankaku.length(); i++) {
			final String charAtString = String.valueOf(hankaku.charAt(i));
			if (hankakuList.contains(charAtString)) {
				builder.append(StringUtils.HALF_FULL_CONVERTAR.inverse().get(charAtString));
			} else {
				builder.append(charAtString);
			}
		}
		return builder.toString();
	}
}
