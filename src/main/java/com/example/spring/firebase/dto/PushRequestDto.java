package com.example.spring.firebase.dto;

public class PushRequestDto {

//	// PUSH通知要求通番(not null) (通番は終了番号に達すると、開始番号から繰返し採番。)
	private Long pushRequestSeq;
//
//	// デバイストークン(not null)
//	private String deviceToken = null;

	// 申込端末種別(not null) (01:iPhoneApp. 02:AndroidApp, 03:PCサイト, 04:携帯サイト, 05:スマホサイト, 06:旧ネコピット, 07:新ネコピット, 08:CVS)
	private String terminalType = null;

//	// 申込端末識別ID(not null)
//	private String terminalId = null;
//
//	// 伝票番号
//	private String denpyoNo = null;
//
//	// 顧客コード
//	private BigDecimal customerCode = null;
//
//	// 連携種別
//	private String renkeiType = null;

	// ポップアップテキスト (PUSH通知でポップアップするテキスト)
	private String popupText = null;

	// タイトル (アプリのお知らせ一覧に表示されるタイトル)
	private String title = null;

	// コンテンツ（本文） (アプリのお知らせに表示される本文)
	private String content = null;

//	// 試行回数(not null) (PUSH通知要求ごとに+1)
//	private BigDecimal tryCnt = null;
//
//	// HTTPステータス (一度もPUSH通知要求をしていない場合はnull)
//	private BigDecimal httpStatus = null;
//
//	// エラーコード (一度もPUSH通知要求をしていない場合はnull)
//	private String errorCd = null;
//
//	// データ作成日時(not null)
//	private Timestamp createDt = null;
//
//	// データ作成プログラムID(not null)
//	private String createId = null;
//
//	// データ更新日時
//	private Timestamp updateDt = null;
//
//	// データ更新プログラムID
//	private String updateId = null;

	/** PUSH通知要求通番(not null) (通番は終了番号に達すると、開始番号から繰返し採番。) を返す。 */
	public Long getPushRequestSeq() {
		return this.pushRequestSeq;
	}

	/** PUSH通知要求通番(not null) (通番は終了番号に達すると、開始番号から繰返し採番。) を設定する。 */
	public void setPushRequestSeq(Long pushRequestSeq) {
		this.pushRequestSeq = pushRequestSeq;
	}
//
//	/** デバイストークン(not null) を返す。 */
//	public String getDeviceToken() {
//		return this.deviceToken;
//	}
//
//	/** デバイストークン(not null) を設定する。 */
//	public void setDeviceToken(String deviceToken) {
//		this.deviceToken = deviceToken;
//	}

	/** 申込端末種別(not null) (01:iPhoneApp. 02:AndroidApp, 03:PCサイト, 04:携帯サイト, 05:スマホサイト, 06:旧ネコピット, 07:新ネコピット, 08:CVS) を返す。 */
	public String getTerminalType() {
		return this.terminalType;
	}

	/** 申込端末種別(not null) (01:iPhoneApp. 02:AndroidApp, 03:PCサイト, 04:携帯サイト, 05:スマホサイト, 06:旧ネコピット, 07:新ネコピット, 08:CVS) を設定する。 */
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

//	/** 申込端末識別ID(not null) を返す。 */
//	public String getTerminalId() {
//		return this.terminalId;
//	}
//
//	/** 申込端末識別ID(not null) を設定する。 */
//	public void setTerminalId(String terminalId) {
//		this.terminalId = terminalId;
//	}
//
//	/** 伝票番号 を返す。 */
//	public String getDenpyoNo() {
//		return this.denpyoNo;
//	}
//
//	/** 伝票番号 を設定する。 */
//	public void setDenpyoNo(String denpyoNo) {
//		this.denpyoNo = denpyoNo;
//	}
//
//	/** 顧客コードを返す。 */
//	public BigDecimal getCustomerCode() {
//		return this.customerCode;
//	}
//
//	/** 顧客コード を設定する。 */
//	public void setCustomerCode(BigDecimal customerCode) {
//		this.customerCode = customerCode;
//	}
//
//	/** 連携種別 を返す。 */
//	public String getRenkeiType() {
//		return this.renkeiType;
//	}
//
//	/** 連携種別 を設定する。 */
//	public void setRenkeiType(String renkeiType) {
//		this.renkeiType = renkeiType;
//	}

	/** ポップアップテキスト (PUSH通知でポップアップするテキスト) を返す。 */
	public String getPopupText() {
		return this.popupText;
	}

	/** ポップアップテキスト (PUSH通知でポップアップするテキスト) を設定する。 */
	public void setPopupText(String popupText) {
		this.popupText = popupText;
	}

	/** タイトル (アプリのお知らせ一覧に表示されるタイトル) を返す。 */
	public String getTitle() {
		return this.title;
	}

	/** タイトル (アプリのお知らせ一覧に表示されるタイトル) を設定する。 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** コンテンツ（本文） (アプリのお知らせに表示される本文) を返す。 */
	public String getContent() {
		return this.content;
	}

	/** コンテンツ（本文） (アプリのお知らせに表示される本文) を設定する。 */
	public void setContent(String content) {
		this.content = content;
	}

	/** 試行回数(not null) (PUSH通知要求ごとに+1) を返す。 */
//	public BigDecimal getTryCnt() {
//		return this.tryCnt;
//	}
//
//	/** 試行回数(not null) (PUSH通知要求ごとに+1) を設定する。 */
//	public void setTryCnt(BigDecimal tryCnt) {
//		this.tryCnt = tryCnt;
//	}
//
//	/** HTTPステータス (一度もPUSH通知要求をしていない場合はnull) を返す。 */
//	public BigDecimal getHttpStatus() {
//		return this.httpStatus;
//	}
//
//	/** HTTPステータス (一度もPUSH通知要求をしていない場合はnull) を設定する。 */
//	public void setHttpStatus(BigDecimal httpStatus) {
//		this.httpStatus = httpStatus;
//	}
//
//	/** エラーコード (一度もPUSH通知要求をしていない場合はnull) を返す。 */
//	public String getErrorCd() {
//		return this.errorCd;
//	}
//
//	/** エラーコード (一度もPUSH通知要求をしていない場合はnull) を設定する。 */
//	public void setErrorCd(String errorCd) {
//		this.errorCd = errorCd;
//	}
//
//	/** データ作成日時(not null) を返す。 */
//	public Timestamp getCreateDt() {
//		return this.createDt;
//	}
//
//	/** データ作成日時(not null) を設定する。 */
//	public void setCreateDt(Timestamp createDt) {
//		this.createDt = createDt;
//	}
//
//	/** データ作成プログラムID(not null) を返す。 */
//	public String getCreateId() {
//		return this.createId;
//	}
//
//	/** データ作成プログラムID(not null) を設定する。 */
//	public void setCreateId(String createId) {
//		this.createId = createId;
//	}
//
//	/** データ更新日時 を返す。 */
//	public Timestamp getUpdateDt() {
//		return this.updateDt;
//	}
//
//	/** データ更新日時 を設定する。 */
//	public void setUpdateDt(Timestamp updateDt) {
//		this.updateDt = updateDt;
//	}
//
//	/** データ更新プログラムID を返す。 */
//	public String getUpdateId() {
//		return this.updateId;
//	}
//
//	/** データ更新プログラムID を設定する。 */
//	public void setUpdateId(String updateId) {
//		this.updateId = updateId;
//	}

}
