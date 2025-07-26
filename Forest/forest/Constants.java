package forest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class Constants {
	/**
	 * このクラスはインスタンスを生成する必要がないため、コンストラクタをprivateにしています。
	 */
	private Constants() {
	}

	/**
	 * 樹状整列データファイル中のタグ「ツリー」を表します。
	 */
	public static final String TagOfTrees = "trees:";

	/**
	 * 樹状整列データファイル中のタグ「ノード」を表します。
	 */
	public static final String TagOfNodes = "nodes:";

	/**
	 * 樹状整列データファイル中のタグ「ブランチ」を表します。
	 */
	public static final String TagOfBranches = "branches:";

	/**
	 * ノードを描く際のラベルの文字色を表します。
	 */
	public static final Color ForegroundColor = Color.BLACK;

	/**
	 * ノードを描く際のラベルの背景色を表します。
	 */
	public static final Color BackgroundColor = Color.WHITE;

	/**
	 * ノードを描く際のラベルのフォントを表します。
	 */
	public static final Font DefaultFont = new Font("Serif", Font.PLAIN, 12);

	/**
	 * ノードにおいてラベルを描く際の枠縁から余裕 (マージン) を表します。
	 */
	public static final Point Margin = new Point(4, 2);

	/**
	 * ノード群を樹状に整列させる際にノード同士の間隔を表します。
	 */
	public static final Point Interval = new Point(25, 2);

	/**
	 * ノード群を深さ優先にたどる際の状態「未定」を表します。
	 */
	public static final Integer UnKnown = Integer.valueOf(0);

	/**
	 * ノード群を深さ優先にたどる際の状態「未訪問」を表します。
	 */
	public static final Integer UnVisited = Integer.valueOf(1);

	/**
	 * ノード群を深さ優先にたどる際の状態「訪問済」を表します。
	 */
	public static final Integer Visited = Integer.valueOf(2);

	/**
	 * 樹状整列アニメーションのチックタック: 時間間隔: スピードを表します。
	 * (ミリ秒単位)
	 */
	public static final Integer SleepTick = Integer.valueOf(50);
}
