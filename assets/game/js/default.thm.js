window.skins=window.skins||{};
                function __extends(d, b) {
                    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
                        function __() {
                            this.constructor = d;
                        }
                    __.prototype = b.prototype;
                    d.prototype = new __();
                };
                window.generateEUI = {};
                generateEUI.paths = {};
                generateEUI.styles = undefined;
                generateEUI.skins = {"eui.Button":"resource/eui_skins/ButtonSkin.exml","eui.CheckBox":"resource/eui_skins/CheckBoxSkin.exml","eui.HScrollBar":"resource/eui_skins/HScrollBarSkin.exml","eui.HSlider":"resource/eui_skins/HSliderSkin.exml","eui.Panel":"resource/eui_skins/PanelSkin.exml","eui.TextInput":"resource/eui_skins/TextInputSkin.exml","eui.ProgressBar":"resource/eui_skins/ProgressBarSkin.exml","eui.RadioButton":"resource/eui_skins/RadioButtonSkin.exml","eui.Scroller":"resource/eui_skins/ScrollerSkin.exml","eui.ToggleSwitch":"resource/eui_skins/ToggleSwitchSkin.exml","eui.VScrollBar":"resource/eui_skins/VScrollBarSkin.exml","eui.VSlider":"resource/eui_skins/VSliderSkin.exml","eui.ItemRenderer":"resource/eui_skins/ItemRendererSkin.exml","TheFarmLobby":"resource/eui_skins/TheFarmLobby.exml","package":"resource/eui_skins/package.exml","Package":"resource/eui_skins/Package.exml","a":"resource/eui_skins/a.exml","Toast":"resource/eui_skins/Toast.exml","friend_farm":"resource/eui_skins/friend_farm.exml"};generateEUI.paths['resource/eui_skins/ButtonSkin.exml'] = window.skins.ButtonSkin = (function (_super) {
	__extends(ButtonSkin, _super);
	function ButtonSkin() {
		_super.call(this);
		this.skinParts = ["tuDisplay","labelDisplay","iconDisplay"];
		
		this.minHeight = 50;
		this.minWidth = 100;
		this.elementsContent = [this.tuDisplay_i(),this.labelDisplay_i(),this.iconDisplay_i()];
		this.states = [
			new eui.State ("up",
				[
				])
			,
			new eui.State ("down",
				[
					new eui.SetProperty("tuDisplay","source","button_down_png")
				])
			,
			new eui.State ("disabled",
				[
					new eui.SetProperty("tuDisplay","alpha",0.5)
				])
		];
	}
	var _proto = ButtonSkin.prototype;

	_proto.tuDisplay_i = function () {
		var t = new eui.Image();
		this.tuDisplay = t;
		t.anchorOffsetX = 50;
		t.anchorOffsetY = 24;
		t.percentHeight = 100;
		t.pixelHitTest = true;
		t.scale9Grid = new egret.Rectangle(1,3,8,8);
		t.source = "button_up_png";
		t.percentWidth = 100;
		t.x = 50;
		t.y = 24;
		return t;
	};
	_proto.labelDisplay_i = function () {
		var t = new eui.Label();
		this.labelDisplay = t;
		t.anchorOffsetX = 42;
		t.anchorOffsetY = 16;
		t.bottom = 8;
		t.left = 8;
		t.right = 8;
		t.size = 18;
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.top = 8;
		t.verticalAlign = "middle";
		return t;
	};
	_proto.iconDisplay_i = function () {
		var t = new eui.Image();
		this.iconDisplay = t;
		t.horizontalCenter = 0;
		t.pixelHitTest = true;
		t.verticalCenter = 0;
		return t;
	};
	return ButtonSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/CheckBoxSkin.exml'] = window.skins.CheckBoxSkin = (function (_super) {
	__extends(CheckBoxSkin, _super);
	function CheckBoxSkin() {
		_super.call(this);
		this.skinParts = ["labelDisplay"];
		
		this.elementsContent = [this._Group1_i()];
		this.states = [
			new eui.State ("up",
				[
				])
			,
			new eui.State ("down",
				[
					new eui.SetProperty("_Image1","alpha",0.7)
				])
			,
			new eui.State ("disabled",
				[
					new eui.SetProperty("_Image1","alpha",0.5)
				])
			,
			new eui.State ("upAndSelected",
				[
					new eui.SetProperty("_Image1","source","checkbox_select_up_png")
				])
			,
			new eui.State ("downAndSelected",
				[
					new eui.SetProperty("_Image1","source","checkbox_select_down_png")
				])
			,
			new eui.State ("disabledAndSelected",
				[
					new eui.SetProperty("_Image1","source","checkbox_select_disabled_png")
				])
		];
	}
	var _proto = CheckBoxSkin.prototype;

	_proto._Group1_i = function () {
		var t = new eui.Group();
		t.percentHeight = 100;
		t.percentWidth = 100;
		t.layout = this._HorizontalLayout1_i();
		t.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
		return t;
	};
	_proto._HorizontalLayout1_i = function () {
		var t = new eui.HorizontalLayout();
		t.verticalAlign = "middle";
		return t;
	};
	_proto._Image1_i = function () {
		var t = new eui.Image();
		this._Image1 = t;
		t.alpha = 1;
		t.fillMode = "scale";
		t.source = "checkbox_unselect_png";
		return t;
	};
	_proto.labelDisplay_i = function () {
		var t = new eui.Label();
		this.labelDisplay = t;
		t.fontFamily = "Tahoma";
		t.size = 20;
		t.textAlign = "center";
		t.textColor = 0x707070;
		t.verticalAlign = "middle";
		return t;
	};
	return CheckBoxSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/friend_farm.exml'] = window.friend_farmSkin = (function (_super) {
	__extends(friend_farmSkin, _super);
	var friend_farmSkin$Skin1 = 	(function (_super) {
		__extends(friend_farmSkin$Skin1, _super);
		function friend_farmSkin$Skin1() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = friend_farmSkin$Skin1.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fangzi@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return friend_farmSkin$Skin1;
	})(eui.Skin);

	var friend_farmSkin$Skin2 = 	(function (_super) {
		__extends(friend_farmSkin$Skin2, _super);
		function friend_farmSkin$Skin2() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = friend_farmSkin$Skin2.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "wabao@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return friend_farmSkin$Skin2;
	})(eui.Skin);

	var friend_farmSkin$Skin3 = 	(function (_super) {
		__extends(friend_farmSkin$Skin3, _super);
		function friend_farmSkin$Skin3() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = friend_farmSkin$Skin3.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jXX@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return friend_farmSkin$Skin3;
	})(eui.Skin);

	var friend_farmSkin$Skin4 = 	(function (_super) {
		__extends(friend_farmSkin$Skin4, _super);
		function friend_farmSkin$Skin4() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = friend_farmSkin$Skin4.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fanhui@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return friend_farmSkin$Skin4;
	})(eui.Skin);

	var friend_farmSkin$Skin5 = 	(function (_super) {
		__extends(friend_farmSkin$Skin5, _super);
		function friend_farmSkin$Skin5() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = friend_farmSkin$Skin5.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return friend_farmSkin$Skin5;
	})(eui.Skin);

	var friend_farmSkin$Skin6 = 	(function (_super) {
		__extends(friend_farmSkin$Skin6, _super);
		function friend_farmSkin$Skin6() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = friend_farmSkin$Skin6.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return friend_farmSkin$Skin6;
	})(eui.Skin);

	function friend_farmSkin() {
		_super.call(this);
		this.skinParts = ["BG","rotate0","Return","farmland1","farmland2","farmland3","farmland4","farmland5","farmland6","farmland7","farmland8","farmland9","rail","LandBuy","land1","land2","land3","land4","land5","land6","land7","land8","land9","rare1","rare2","rare3","rare4","rare5","rare6","rare7","rare8","rare9","ripe1","ripe2","ripe3","ripe4","ripe5","ripe6","ripe7","ripe8","ripe9","cover1","cover2","cover3","cover4","cover5","cover6","cover7","cover8","cover9","dogbowl0","zhai1","zhai2","zhai3","zhai4","zhai5","zhai6","zhai7","zhai8","zhai9","IndianArrowheads","dog","rotate1","kz1","kz2","kz3","kz4","kz5","kz6","kz7","kz8","kz9","dagoubang0","gouyidong","rollxd","roll","NameBackground","Name","daojukuang1","daoju1","daojukuang2","daoju2","FHZSJBJ0","FHZSJ0","HeadPortrait","FHZSJBJ","FHZSJ","LV","fanhui","Money","tishixinxi_text","tishixinxi_colse","tishixinxi_yes","tishixinxi"];
		
		this.height = 667;
		this.width = 375;
		this.elementsContent = [this.roll_i(),this.NameBackground_i(),this.Name_i(),this.daojukuang1_i(),this.daoju1_i(),this.daojukuang2_i(),this.daoju2_i(),this.FHZSJBJ0_i(),this.FHZSJ0_i(),this.HeadPortrait_i(),this._Image7_i(),this._Button1_i(),this.FHZSJBJ_i(),this.FHZSJ_i(),this._Image8_i(),this._Image9_i(),this.LV_i(),this.fanhui_i(),this.Money_i(),this.tishixinxi_i()];
	}
	var _proto = friend_farmSkin.prototype;

	_proto.roll_i = function () {
		var t = new eui.Scroller();
		this.roll = t;
		t.bottom = 0;
		t.bounces = false;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.viewport = this.rollxd_i();
		return t;
	};
	_proto.rollxd_i = function () {
		var t = new eui.Group();
		this.rollxd = t;
		t.elementsContent = [this.BG_i(),this._Image1_i(),this.rotate0_i(),this.Return_i(),this._Image2_i(),this.farmland1_i(),this.farmland2_i(),this.farmland3_i(),this.farmland4_i(),this.farmland5_i(),this.farmland6_i(),this.farmland7_i(),this.farmland8_i(),this.farmland9_i(),this.rail_i(),this.LandBuy_i(),this.land1_i(),this.land2_i(),this.land3_i(),this.land4_i(),this.land5_i(),this.land6_i(),this.land7_i(),this.land8_i(),this.land9_i(),this.rare1_i(),this.rare2_i(),this.rare3_i(),this.rare4_i(),this.rare5_i(),this.rare6_i(),this.rare7_i(),this.rare8_i(),this.rare9_i(),this.ripe1_i(),this.ripe2_i(),this.ripe3_i(),this.ripe4_i(),this.ripe5_i(),this.ripe6_i(),this.ripe7_i(),this.ripe8_i(),this.ripe9_i(),this.cover1_i(),this.cover2_i(),this.cover3_i(),this.cover4_i(),this.cover5_i(),this.cover6_i(),this.cover7_i(),this.cover8_i(),this.cover9_i(),this.dogbowl0_i(),this.zhai1_i(),this.zhai2_i(),this.zhai3_i(),this.zhai4_i(),this.zhai5_i(),this.zhai6_i(),this.zhai7_i(),this.zhai8_i(),this.zhai9_i(),this.IndianArrowheads_i(),this._Image3_i(),this.dog_i(),this._Image4_i(),this.rotate1_i(),this._Image5_i(),this._Image6_i(),this.kz1_i(),this.kz2_i(),this.kz3_i(),this.kz4_i(),this.kz5_i(),this.kz6_i(),this.kz7_i(),this.kz8_i(),this.kz9_i(),this.gouyidong_i()];
		return t;
	};
	_proto.BG_i = function () {
		var t = new eui.Image();
		this.BG = t;
		t.height = 850;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing@2x(1)_png";
		t.width = 900;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 44;
		t.source = "fengche@3x_png";
		t.width = 35;
		t.x = 476;
		t.y = 300;
		return t;
	};
	_proto.rotate0_i = function () {
		var t = new eui.Image();
		this.rotate0 = t;
		t.anchorOffsetX = 19.39;
		t.anchorOffsetY = 14.53;
		t.height = 29;
		t.source = "fengye@3x_png";
		t.width = 35;
		t.x = 499.39;
		t.y = 310.34;
		return t;
	};
	_proto.Return_i = function () {
		var t = new eui.Button();
		this.Return = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 202;
		t.label = "";
		t.width = 197;
		t.x = 659;
		t.y = 311.5;
		t.skinName = friend_farmSkin$Skin1;
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 167;
		t.source = "BJCD@3x_png";
		t.width = 287;
		t.x = 444;
		t.y = 465;
		return t;
	};
	_proto.farmland1_i = function () {
		var t = new eui.Image();
		this.farmland1 = t;
		t.height = 42;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 78;
		t.x = 546;
		t.y = 471;
		return t;
	};
	_proto.farmland2_i = function () {
		var t = new eui.Image();
		this.farmland2 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 500;
		t.y = 493;
		return t;
	};
	_proto.farmland3_i = function () {
		var t = new eui.Image();
		this.farmland3 = t;
		t.height = 42;
		t.rotation = 359.92;
		t.source = "weizhongzhi_png";
		t.width = 83;
		t.x = 592;
		t.y = 496;
		return t;
	};
	_proto.farmland4_i = function () {
		var t = new eui.Image();
		this.farmland4 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 453;
		t.y = 521;
		return t;
	};
	_proto.farmland5_i = function () {
		var t = new eui.Image();
		this.farmland5 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 547;
		t.y = 518;
		return t;
	};
	_proto.farmland6_i = function () {
		var t = new eui.Image();
		this.farmland6 = t;
		t.height = 47;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 86;
		t.x = 639;
		t.y = 521;
		return t;
	};
	_proto.farmland7_i = function () {
		var t = new eui.Image();
		this.farmland7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 51;
		t.rotation = 359.92;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 499;
		t.y = 545;
		return t;
	};
	_proto.farmland8_i = function () {
		var t = new eui.Image();
		this.farmland8 = t;
		t.height = 45;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 83;
		t.x = 592;
		t.y = 547;
		return t;
	};
	_proto.farmland9_i = function () {
		var t = new eui.Image();
		this.farmland9 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 547;
		t.y = 571;
		return t;
	};
	_proto.rail_i = function () {
		var t = new eui.Image();
		this.rail = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 274;
		t.pixelHitTest = true;
		t.rotation = 0;
		t.source = "liba@3x_png";
		t.width = 390;
		t.x = 384;
		t.y = 398;
		return t;
	};
	_proto.LandBuy_i = function () {
		var t = new eui.Image();
		this.LandBuy = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 44;
		t.pixelHitTest = false;
		t.source = "zhongzhi@3x_png";
		t.width = 49;
		t.x = 564;
		t.y = 504;
		return t;
	};
	_proto.land1_i = function () {
		var t = new eui.Image();
		this.land1 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 572;
		t.y = 472;
		return t;
	};
	_proto.land2_i = function () {
		var t = new eui.Image();
		this.land2 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 524.32;
		t.y = 498.33;
		return t;
	};
	_proto.land3_i = function () {
		var t = new eui.Image();
		this.land3 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 617.17;
		t.y = 496.98;
		return t;
	};
	_proto.land4_i = function () {
		var t = new eui.Image();
		this.land4 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 477.02;
		t.y = 528.68;
		return t;
	};
	_proto.land5_i = function () {
		var t = new eui.Image();
		this.land5 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 573.7;
		t.y = 525.33;
		return t;
	};
	_proto.land6_i = function () {
		var t = new eui.Image();
		this.land6 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 670.67;
		t.y = 524.67;
		return t;
	};
	_proto.land7_i = function () {
		var t = new eui.Image();
		this.land7 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 527.04;
		t.y = 552.35;
		return t;
	};
	_proto.land8_i = function () {
		var t = new eui.Image();
		this.land8 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 621.71;
		t.y = 549.71;
		return t;
	};
	_proto.land9_i = function () {
		var t = new eui.Image();
		this.land9 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 574.5;
		t.y = 574.41;
		return t;
	};
	_proto.rare1_i = function () {
		var t = new eui.Image();
		this.rare1 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 564.67;
		t.y = 451.02;
		return t;
	};
	_proto.rare2_i = function () {
		var t = new eui.Image();
		this.rare2 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 519.37;
		t.y = 475;
		return t;
	};
	_proto.rare3_i = function () {
		var t = new eui.Image();
		this.rare3 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 613.31;
		t.y = 474.33;
		return t;
	};
	_proto.rare4_i = function () {
		var t = new eui.Image();
		this.rare4 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 472.66;
		t.y = 506;
		return t;
	};
	_proto.rare5_i = function () {
		var t = new eui.Image();
		this.rare5 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 570;
		t.y = 504.66;
		return t;
	};
	_proto.rare6_i = function () {
		var t = new eui.Image();
		this.rare6 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 663;
		t.y = 502;
		return t;
	};
	_proto.rare7_i = function () {
		var t = new eui.Image();
		this.rare7 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 520.01;
		t.y = 528.67;
		return t;
	};
	_proto.rare8_i = function () {
		var t = new eui.Image();
		this.rare8 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 617.33;
		t.y = 527.01;
		return t;
	};
	_proto.rare9_i = function () {
		var t = new eui.Image();
		this.rare9 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 570.02;
		t.y = 557.01;
		return t;
	};
	_proto.ripe1_i = function () {
		var t = new eui.Image();
		this.ripe1 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 564;
		t.y = 446.34;
		return t;
	};
	_proto.ripe2_i = function () {
		var t = new eui.Image();
		this.ripe2 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 521.32;
		t.y = 469.68;
		return t;
	};
	_proto.ripe3_i = function () {
		var t = new eui.Image();
		this.ripe3 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 612.34;
		t.y = 469.67;
		return t;
	};
	_proto.ripe4_i = function () {
		var t = new eui.Image();
		this.ripe4 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 471.66;
		t.y = 497.68;
		return t;
	};
	_proto.ripe5_i = function () {
		var t = new eui.Image();
		this.ripe5 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 566.34;
		t.y = 498.33;
		return t;
	};
	_proto.ripe6_i = function () {
		var t = new eui.Image();
		this.ripe6 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 660.67;
		t.y = 496.19;
		return t;
	};
	_proto.ripe7_i = function () {
		var t = new eui.Image();
		this.ripe7 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 520.33;
		t.y = 525.66;
		return t;
	};
	_proto.ripe8_i = function () {
		var t = new eui.Image();
		this.ripe8 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 615.33;
		t.y = 521.67;
		return t;
	};
	_proto.ripe9_i = function () {
		var t = new eui.Image();
		this.ripe9 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 569.34;
		t.y = 548.03;
		return t;
	};
	_proto.cover1_i = function () {
		var t = new eui.Image();
		this.cover1 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 534;
		t.y = 435;
		return t;
	};
	_proto.cover2_i = function () {
		var t = new eui.Image();
		this.cover2 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 492;
		t.y = 457;
		return t;
	};
	_proto.cover3_i = function () {
		var t = new eui.Image();
		this.cover3 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 582;
		t.y = 459;
		return t;
	};
	_proto.cover4_i = function () {
		var t = new eui.Image();
		this.cover4 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 444;
		t.y = 485;
		return t;
	};
	_proto.cover5_i = function () {
		var t = new eui.Image();
		this.cover5 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 539;
		t.y = 486;
		return t;
	};
	_proto.cover6_i = function () {
		var t = new eui.Image();
		this.cover6 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 631;
		t.y = 486;
		return t;
	};
	_proto.cover7_i = function () {
		var t = new eui.Image();
		this.cover7 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 491;
		t.y = 513;
		return t;
	};
	_proto.cover8_i = function () {
		var t = new eui.Image();
		this.cover8 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 585;
		t.y = 510;
		return t;
	};
	_proto.cover9_i = function () {
		var t = new eui.Image();
		this.cover9 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 538;
		t.y = 539;
		return t;
	};
	_proto.dogbowl0_i = function () {
		var t = new eui.Image();
		this.dogbowl0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "gouliang@3x_png";
		t.width = 28;
		t.x = 489;
		t.y = 654;
		return t;
	};
	_proto.zhai1_i = function () {
		var t = new eui.Image();
		this.zhai1 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 566.01;
		t.y = 406;
		return t;
	};
	_proto.zhai2_i = function () {
		var t = new eui.Image();
		this.zhai2 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 523.34;
		t.y = 428;
		return t;
	};
	_proto.zhai3_i = function () {
		var t = new eui.Image();
		this.zhai3 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 615.34;
		t.y = 428;
		return t;
	};
	_proto.zhai4_i = function () {
		var t = new eui.Image();
		this.zhai4 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 473.67;
		t.y = 456;
		return t;
	};
	_proto.zhai5_i = function () {
		var t = new eui.Image();
		this.zhai5 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 568;
		t.y = 457;
		return t;
	};
	_proto.zhai6_i = function () {
		var t = new eui.Image();
		this.zhai6 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 663;
		t.y = 454;
		return t;
	};
	_proto.zhai7_i = function () {
		var t = new eui.Image();
		this.zhai7 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 522;
		t.y = 483;
		return t;
	};
	_proto.zhai8_i = function () {
		var t = new eui.Image();
		this.zhai8 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 616;
		t.y = 479;
		return t;
	};
	_proto.zhai9_i = function () {
		var t = new eui.Image();
		this.zhai9 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 570;
		t.y = 507;
		return t;
	};
	_proto.IndianArrowheads_i = function () {
		var t = new eui.Button();
		this.IndianArrowheads = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 175;
		t.label = "";
		t.touchChildren = true;
		t.width = 269;
		t.x = 130;
		t.y = 349;
		t.skinName = friend_farmSkin$Skin2;
		return t;
	};
	_proto._Image3_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 60;
		t.source = "yutang@3x_png";
		t.width = 67;
		t.x = 261;
		t.y = 690;
		return t;
	};
	_proto.dog_i = function () {
		var t = new eui.Image();
		this.dog = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 55;
		t.rotation = 0;
		t.scaleX = 1.1;
		t.scaleY = 1.15;
		t.source = "erha@3x_png";
		t.visible = false;
		t.width = 53;
		t.x = 416;
		t.y = 620;
		return t;
	};
	_proto._Image4_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 47;
		t.source = "fengche@3x_png";
		t.width = 39;
		t.x = 206;
		t.y = 291;
		return t;
	};
	_proto.rotate1_i = function () {
		var t = new eui.Image();
		this.rotate1 = t;
		t.anchorOffsetX = 18.31;
		t.anchorOffsetY = 14.7;
		t.height = 29;
		t.rotation = 0;
		t.source = "fengye@3x_png";
		t.width = 33;
		t.x = 231.78;
		t.y = 301.42;
		return t;
	};
	_proto._Image5_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 209;
		t.source = "2@3x_png";
		t.width = 900;
		t.x = 0;
		t.y = 239;
		return t;
	};
	_proto._Image6_i = function () {
		var t = new eui.Image();
		t.height = 113;
		t.source = "XFZ@3x_png";
		t.width = 117;
		t.x = 756.5;
		t.y = 418;
		return t;
	};
	_proto.kz1_i = function () {
		var t = new eui.Label();
		this.kz1 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 572;
		t.y = 416;
		return t;
	};
	_proto.kz2_i = function () {
		var t = new eui.Label();
		this.kz2 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 529;
		t.y = 439;
		return t;
	};
	_proto.kz3_i = function () {
		var t = new eui.Label();
		this.kz3 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 622;
		t.y = 439;
		return t;
	};
	_proto.kz4_i = function () {
		var t = new eui.Label();
		this.kz4 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 480;
		t.y = 467;
		return t;
	};
	_proto.kz5_i = function () {
		var t = new eui.Label();
		this.kz5 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 574;
		t.y = 467;
		return t;
	};
	_proto.kz6_i = function () {
		var t = new eui.Label();
		this.kz6 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 668;
		t.y = 466;
		return t;
	};
	_proto.kz7_i = function () {
		var t = new eui.Label();
		this.kz7 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 528;
		t.y = 493;
		return t;
	};
	_proto.kz8_i = function () {
		var t = new eui.Label();
		this.kz8 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 622;
		t.y = 489;
		return t;
	};
	_proto.kz9_i = function () {
		var t = new eui.Label();
		this.kz9 = t;
		t.height = 12;
		t.size = 12;
		t.text = "可摘";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 24;
		t.x = 577;
		t.y = 517;
		return t;
	};
	_proto.gouyidong_i = function () {
		var t = new eui.Group();
		this.gouyidong = t;
		t.height = 850;
		t.touchThrough = true;
		t.width = 900;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.dagoubang0_i()];
		return t;
	};
	_proto.dagoubang0_i = function () {
		var t = new eui.Image();
		this.dagoubang0 = t;
		t.height = 20;
		t.source = "goubang@3x_daoju_png";
		t.visible = false;
		t.width = 20;
		t.x = 87;
		t.y = 84;
		return t;
	};
	_proto.NameBackground_i = function () {
		var t = new eui.Image();
		this.NameBackground = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "jjjjjjjxxxxxxx_png";
		t.width = 60;
		t.x = 11.68;
		t.y = 85;
		return t;
	};
	_proto.Name_i = function () {
		var t = new eui.Label();
		this.Name = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 58;
		t.x = 12.34;
		t.y = 86;
		return t;
	};
	_proto.daojukuang1_i = function () {
		var t = new eui.Image();
		this.daojukuang1 = t;
		t.height = 23;
		t.source = "juxingSXDJKJ@3x_png";
		t.visible = false;
		t.width = 23;
		t.x = 94;
		t.y = 48;
		return t;
	};
	_proto.daoju1_i = function () {
		var t = new eui.Image();
		this.daoju1 = t;
		t.height = 20;
		t.source = "mofachuji@3xCJMFD_png";
		t.visible = false;
		t.width = 20;
		t.x = 96;
		t.y = 50;
		return t;
	};
	_proto.daojukuang2_i = function () {
		var t = new eui.Image();
		this.daojukuang2 = t;
		t.height = 23;
		t.source = "juxingSXDJKJ@3x_png";
		t.visible = false;
		t.width = 23;
		t.x = 131;
		t.y = 48;
		return t;
	};
	_proto.daoju2_i = function () {
		var t = new eui.Image();
		this.daoju2 = t;
		t.height = 20;
		t.source = "chujigouliang@3xCJGL_png";
		t.visible = false;
		t.width = 20;
		t.x = 134;
		t.y = 50;
		return t;
	};
	_proto.FHZSJBJ0_i = function () {
		var t = new eui.Image();
		this.FHZSJBJ0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.rotation = 180;
		t.source = "DJJJMP_png";
		t.visible = false;
		t.width = 132;
		t.x = 192;
		t.y = 103;
		return t;
	};
	_proto.FHZSJ0_i = function () {
		var t = new eui.Label();
		this.FHZSJ0 = t;
		t.size = 11;
		t.text = "截止时间：10月41日8h";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 68;
		t.y = 87;
		return t;
	};
	_proto.HeadPortrait_i = function () {
		var t = new eui.Image();
		this.HeadPortrait = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 54;
		t.source = "white2_png";
		t.width = 60;
		t.x = 18;
		t.y = 14;
		return t;
	};
	_proto._Image7_i = function () {
		var t = new eui.Image();
		t.height = 64;
		t.pixelHitTest = true;
		t.source = "juxingBK@3x_png";
		t.width = 70;
		t.x = 13;
		t.y = 10;
		return t;
	};
	_proto._Button1_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.label = "";
		t.scaleX = 0.9;
		t.width = 75;
		t.x = 196;
		t.y = 18;
		t.skinName = friend_farmSkin$Skin3;
		return t;
	};
	_proto.FHZSJBJ_i = function () {
		var t = new eui.Image();
		this.FHZSJBJ = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.rotation = 180;
		t.source = "DJJJMP_png";
		t.visible = false;
		t.width = 132;
		t.x = 146;
		t.y = 128;
		return t;
	};
	_proto.FHZSJ_i = function () {
		var t = new eui.Label();
		this.FHZSJ = t;
		t.size = 11;
		t.text = "截止时间：10月41日8h";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 22;
		t.y = 112;
		return t;
	};
	_proto._Image8_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 27;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 20;
		t.x = 188;
		t.y = 11;
		return t;
	};
	_proto._Image9_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "gengduo@3x_png";
		t.width = 29;
		t.x = 251;
		t.y = 14;
		return t;
	};
	_proto.LV_i = function () {
		var t = new eui.Image();
		this.LV = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "";
		t.width = 68;
		t.x = 92;
		t.y = 15;
		return t;
	};
	_proto.fanhui_i = function () {
		var t = new eui.Button();
		this.fanhui = t;
		t.height = 55;
		t.label = "";
		t.width = 40;
		t.x = 324;
		t.y = 12;
		t.skinName = friend_farmSkin$Skin4;
		return t;
	};
	_proto.Money_i = function () {
		var t = new eui.Label();
		this.Money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 10;
		t.size = 13;
		t.text = "1000";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 33;
		t.x = 213;
		t.y = 22;
		return t;
	};
	_proto.tishixinxi_i = function () {
		var t = new eui.Group();
		this.tishixinxi = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image10_i(),this._Image11_i(),this._Image12_i(),this.tishixinxi_text_i(),this.tishixinxi_colse_i(),this.tishixinxi_yes_i()];
		return t;
	};
	_proto._Image10_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image11_i = function () {
		var t = new eui.Image();
		t.height = 225;
		t.source = "beijingBKD@3x_png";
		t.width = 300;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto._Image12_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.source = "tishi_png";
		t.width = 204;
		t.x = 87;
		t.y = 200;
		return t;
	};
	_proto.tishixinxi_text_i = function () {
		var t = new eui.Label();
		this.tishixinxi_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "FangSong";
		t.height = 108;
		t.size = 20;
		t.text = "Label";
		t.textColor = 0x000000;
		t.width = 246;
		t.x = 63;
		t.y = 266;
		return t;
	};
	_proto.tishixinxi_colse_i = function () {
		var t = new eui.Button();
		this.tishixinxi_colse = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 307.01;
		t.y = 209;
		t.skinName = friend_farmSkin$Skin5;
		return t;
	};
	_proto.tishixinxi_yes_i = function () {
		var t = new eui.Button();
		this.tishixinxi_yes = t;
		t.height = 25;
		t.label = "";
		t.width = 65;
		t.x = 155;
		t.y = 381;
		t.skinName = friend_farmSkin$Skin6;
		return t;
	};
	return friend_farmSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/HScrollBarSkin.exml'] = window.skins.HScrollBarSkin = (function (_super) {
	__extends(HScrollBarSkin, _super);
	function HScrollBarSkin() {
		_super.call(this);
		this.skinParts = ["thumb"];
		
		this.minHeight = 8;
		this.minWidth = 20;
		this.elementsContent = [this.thumb_i()];
	}
	var _proto = HScrollBarSkin.prototype;

	_proto.thumb_i = function () {
		var t = new eui.Image();
		this.thumb = t;
		t.height = 8;
		t.scale9Grid = new egret.Rectangle(3,3,2,2);
		t.source = "roundthumb_png";
		t.verticalCenter = 0;
		t.width = 30;
		return t;
	};
	return HScrollBarSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/HSliderSkin.exml'] = window.skins.HSliderSkin = (function (_super) {
	__extends(HSliderSkin, _super);
	function HSliderSkin() {
		_super.call(this);
		this.skinParts = ["track","trackHighlight","thumb"];
		
		this.minHeight = 8;
		this.minWidth = 20;
		this.width = 253;
		this.elementsContent = [this.track_i(),this.trackHighlight_i(),this.thumb_i()];
	}
	var _proto = HSliderSkin.prototype;

	_proto.track_i = function () {
		var t = new eui.Image();
		this.track = t;
		t.height = 12;
		t.left = 0;
		t.scale9Grid = new egret.Rectangle(1,1,4,4);
		t.source = "rectangle3_png";
		t.verticalCenter = 0;
		t.percentWidth = 100;
		return t;
	};
	_proto.trackHighlight_i = function () {
		var t = new eui.Image();
		this.trackHighlight = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 15;
		t.left = 0;
		t.scale9Grid = new egret.Rectangle(1,1,4,4);
		t.source = "rectangle8_png";
		t.verticalCenter = 0;
		t.width = 0;
		return t;
	};
	_proto.thumb_i = function () {
		var t = new eui.Image();
		this.thumb = t;
		t.height = 27;
		t.left = 0;
		t.source = "ball_png";
		t.verticalCenter = 0;
		t.width = 27;
		return t;
	};
	return HSliderSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/ItemRendererSkin.exml'] = window.skins.ItemRendererSkin = (function (_super) {
	__extends(ItemRendererSkin, _super);
	function ItemRendererSkin() {
		_super.call(this);
		this.skinParts = ["labelDisplay"];
		
		this.minHeight = 50;
		this.minWidth = 100;
		this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
		this.states = [
			new eui.State ("up",
				[
				])
			,
			new eui.State ("down",
				[
					new eui.SetProperty("_Image1","source","button_down_png")
				])
			,
			new eui.State ("disabled",
				[
					new eui.SetProperty("_Image1","alpha",0.5)
				])
		];
		
		eui.Binding.$bindProperties(this, ["hostComponent.data"],[0],this.labelDisplay,"text");
	}
	var _proto = ItemRendererSkin.prototype;

	_proto._Image1_i = function () {
		var t = new eui.Image();
		this._Image1 = t;
		t.percentHeight = 100;
		t.scale9Grid = new egret.Rectangle(1,3,8,8);
		t.source = "button_up_png";
		t.percentWidth = 100;
		return t;
	};
	_proto.labelDisplay_i = function () {
		var t = new eui.Label();
		this.labelDisplay = t;
		t.bottom = 8;
		t.fontFamily = "Tahoma";
		t.left = 8;
		t.right = 8;
		t.size = 20;
		t.textAlign = "center";
		t.textColor = 0xFFFFFF;
		t.top = 8;
		t.verticalAlign = "middle";
		return t;
	};
	return ItemRendererSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/Package.exml'] = window.PackageSkin = (function (_super) {
	__extends(PackageSkin, _super);
	function PackageSkin() {
		_super.call(this);
		this.skinParts = ["Package_head","Package_name","Package_number","Package_whether"];
		
		this.height = 100;
		this.width = 540;
		this.elementsContent = [this._Image1_i(),this.Package_head_i(),this.Package_name_i(),this.Package_number_i(),this.Package_whether_i()];
	}
	var _proto = PackageSkin.prototype;

	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.height = 100;
		t.source = "turquoise_png";
		t.width = 540;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.Package_head_i = function () {
		var t = new eui.Image();
		this.Package_head = t;
		t.height = 80;
		t.source = "white2_png";
		t.width = 80;
		t.x = 20;
		t.y = 10;
		return t;
	};
	_proto.Package_name_i = function () {
		var t = new eui.Label();
		this.Package_name = t;
		t.text = "名字";
		t.x = 140;
		t.y = 40;
		return t;
	};
	_proto.Package_number_i = function () {
		var t = new eui.Label();
		this.Package_number = t;
		t.text = "0";
		t.x = 300;
		t.y = 39;
		return t;
	};
	_proto.Package_whether_i = function () {
		var t = new eui.Button();
		this.Package_whether = t;
		t.label = "Button";
		t.x = 400;
		t.y = 25;
		return t;
	};
	return PackageSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/PanelSkin.exml'] = window.skins.PanelSkin = (function (_super) {
	__extends(PanelSkin, _super);
	function PanelSkin() {
		_super.call(this);
		this.skinParts = ["titleDisplay","moveArea","closeButton"];
		
		this.minHeight = 230;
		this.minWidth = 450;
		this.elementsContent = [this._Image1_i(),this.moveArea_i(),this.closeButton_i()];
	}
	var _proto = PanelSkin.prototype;

	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scale9Grid = new egret.Rectangle(2,2,12,12);
		t.source = "border_png";
		t.top = 0;
		return t;
	};
	_proto.moveArea_i = function () {
		var t = new eui.Group();
		this.moveArea = t;
		t.height = 45;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.elementsContent = [this._Image2_i(),this.titleDisplay_i()];
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "header_png";
		t.top = 0;
		return t;
	};
	_proto.titleDisplay_i = function () {
		var t = new eui.Label();
		this.titleDisplay = t;
		t.fontFamily = "Tahoma";
		t.left = 15;
		t.right = 5;
		t.size = 20;
		t.textColor = 0xFFFFFF;
		t.verticalCenter = 0;
		t.wordWrap = false;
		return t;
	};
	_proto.closeButton_i = function () {
		var t = new eui.Button();
		this.closeButton = t;
		t.bottom = 5;
		t.horizontalCenter = 0;
		t.label = "close";
		return t;
	};
	return PanelSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/ProgressBarSkin.exml'] = window.skins.ProgressBarSkin = (function (_super) {
	__extends(ProgressBarSkin, _super);
	function ProgressBarSkin() {
		_super.call(this);
		this.skinParts = ["thumb"];
		
		this.minHeight = 18;
		this.minWidth = 30;
		this.elementsContent = [this._Image1_i(),this.thumb_i()];
	}
	var _proto = ProgressBarSkin.prototype;

	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.percentHeight = 100;
		t.scale9Grid = new egret.Rectangle(1,1,4,4);
		t.source = "jindutiaoxia@3x_png";
		t.verticalCenter = 0;
		t.percentWidth = 100;
		return t;
	};
	_proto.thumb_i = function () {
		var t = new eui.Image();
		this.thumb = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.percentHeight = 80;
		t.left = 0;
		t.source = "jindutiaoshang@3x_png";
		t.verticalCenter = 0;
		t.percentWidth = 98;
		return t;
	};
	return ProgressBarSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/RadioButtonSkin.exml'] = window.skins.RadioButtonSkin = (function (_super) {
	__extends(RadioButtonSkin, _super);
	function RadioButtonSkin() {
		_super.call(this);
		this.skinParts = ["labelDisplay"];
		
		this.elementsContent = [this._Group1_i()];
		this.states = [
			new eui.State ("up",
				[
				])
			,
			new eui.State ("down",
				[
					new eui.SetProperty("_Image1","alpha",0.7)
				])
			,
			new eui.State ("disabled",
				[
					new eui.SetProperty("_Image1","alpha",0.5)
				])
			,
			new eui.State ("upAndSelected",
				[
					new eui.SetProperty("_Image1","source","radiobutton_select_up_png")
				])
			,
			new eui.State ("downAndSelected",
				[
					new eui.SetProperty("_Image1","source","radiobutton_select_down_png")
				])
			,
			new eui.State ("disabledAndSelected",
				[
					new eui.SetProperty("_Image1","source","radiobutton_select_disabled_png")
				])
		];
	}
	var _proto = RadioButtonSkin.prototype;

	_proto._Group1_i = function () {
		var t = new eui.Group();
		t.percentHeight = 100;
		t.percentWidth = 100;
		t.layout = this._HorizontalLayout1_i();
		t.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
		return t;
	};
	_proto._HorizontalLayout1_i = function () {
		var t = new eui.HorizontalLayout();
		t.verticalAlign = "middle";
		return t;
	};
	_proto._Image1_i = function () {
		var t = new eui.Image();
		this._Image1 = t;
		t.alpha = 1;
		t.fillMode = "scale";
		t.source = "radiobutton_unselect_png";
		return t;
	};
	_proto.labelDisplay_i = function () {
		var t = new eui.Label();
		this.labelDisplay = t;
		t.fontFamily = "Tahoma";
		t.size = 20;
		t.textAlign = "center";
		t.textColor = 0x707070;
		t.verticalAlign = "middle";
		return t;
	};
	return RadioButtonSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/ScrollerSkin.exml'] = window.skins.ScrollerSkin = (function (_super) {
	__extends(ScrollerSkin, _super);
	function ScrollerSkin() {
		_super.call(this);
		this.skinParts = ["horizontalScrollBar","verticalScrollBar"];
		
		this.minHeight = 20;
		this.minWidth = 20;
		this.elementsContent = [this.horizontalScrollBar_i(),this.verticalScrollBar_i()];
	}
	var _proto = ScrollerSkin.prototype;

	_proto.horizontalScrollBar_i = function () {
		var t = new eui.HScrollBar();
		this.horizontalScrollBar = t;
		t.autoVisibility = false;
		t.bottom = 0;
		t.visible = false;
		t.percentWidth = 100;
		return t;
	};
	_proto.verticalScrollBar_i = function () {
		var t = new eui.VScrollBar();
		this.verticalScrollBar = t;
		t.autoVisibility = false;
		t.percentHeight = 100;
		t.right = 0;
		t.visible = false;
		return t;
	};
	return ScrollerSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/SomeoneRoom.exml'] = window.SomeoneRoom = (function (_super) {
	__extends(SomeoneRoom, _super);
	var SomeoneRoom$Skin7 = 	(function (_super) {
		__extends(SomeoneRoom$Skin7, _super);
		function SomeoneRoom$Skin7() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = SomeoneRoom$Skin7.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fangzi@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return SomeoneRoom$Skin7;
	})(eui.Skin);

	var SomeoneRoom$Skin8 = 	(function (_super) {
		__extends(SomeoneRoom$Skin8, _super);
		function SomeoneRoom$Skin8() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = SomeoneRoom$Skin8.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "wabao@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return SomeoneRoom$Skin8;
	})(eui.Skin);

	var SomeoneRoom$Skin9 = 	(function (_super) {
		__extends(SomeoneRoom$Skin9, _super);
		function SomeoneRoom$Skin9() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = SomeoneRoom$Skin9.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fanhui@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return SomeoneRoom$Skin9;
	})(eui.Skin);

	function SomeoneRoom() {
		_super.call(this);
		this.skinParts = ["BG","rotate0","Return","farmland1","farmland2","farmland3","farmland4","farmland5","farmland6","farmland7","farmland8","farmland9","LandBuy","land1","land2","land3","land4","land5","land6","land7","land8","land9","rare1","rare2","rare3","rare4","rare5","rare6","rare7","rare8","rare9","ripe1","ripe2","ripe3","ripe4","ripe5","ripe6","ripe7","ripe8","ripe9","dogbowl0","zhai9","zhai10","zhai11","zhai12","zhai13","zhai14","zhai15","zhai16","zhai17","IndianArrowheads","rail","dog","rotate1","rollxd","roll","NameBackground0","LV0","Name0","HeadPortrait0","Money0"];
		
		this.height = 1000;
		this.width = 500;
		this.elementsContent = [this._Image1_i(),this.roll_i(),this.NameBackground0_i(),this.LV0_i(),this.Name0_i(),this.HeadPortrait0_i(),this.Money0_i(),this._Button1_i()];
	}
	var _proto = SomeoneRoom.prototype;

	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.height = 1700;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing@2x(1)_png";
		t.width = 1800;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.roll_i = function () {
		var t = new eui.Scroller();
		this.roll = t;
		t.bounces = false;
		t.height = 1000;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.viewport = this.rollxd_i();
		return t;
	};
	_proto.rollxd_i = function () {
		var t = new eui.Group();
		this.rollxd = t;
		t.y = 2;
		t.elementsContent = [this.BG_i(),this._Image2_i(),this.rotate0_i(),this.Return_i(),this.farmland1_i(),this.farmland2_i(),this.farmland3_i(),this.farmland4_i(),this.farmland5_i(),this.farmland6_i(),this.farmland7_i(),this.farmland8_i(),this.farmland9_i(),this.LandBuy_i(),this.land1_i(),this.land2_i(),this.land3_i(),this.land4_i(),this.land5_i(),this.land6_i(),this.land7_i(),this.land8_i(),this.land9_i(),this.rare1_i(),this.rare2_i(),this.rare3_i(),this.rare4_i(),this.rare5_i(),this.rare6_i(),this.rare7_i(),this.rare8_i(),this.rare9_i(),this.ripe1_i(),this.ripe2_i(),this.ripe3_i(),this.ripe4_i(),this.ripe5_i(),this.ripe6_i(),this.ripe7_i(),this.ripe8_i(),this.ripe9_i(),this.dogbowl0_i(),this.zhai9_i(),this.zhai10_i(),this.zhai11_i(),this.zhai12_i(),this.zhai13_i(),this.zhai14_i(),this.zhai15_i(),this.zhai16_i(),this.zhai17_i(),this.IndianArrowheads_i(),this._Image3_i(),this.rail_i(),this.dog_i(),this._Image4_i(),this.rotate1_i(),this._Image5_i()];
		return t;
	};
	_proto.BG_i = function () {
		var t = new eui.Image();
		this.BG = t;
		t.height = 1700;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing@2x(1)_png";
		t.width = 1800;
		t.x = 0;
		t.y = 1;
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "fengche_png";
		t.width = 74;
		t.x = 910;
		t.y = 593.14;
		return t;
	};
	_proto.rotate0_i = function () {
		var t = new eui.Image();
		this.rotate0 = t;
		t.anchorOffsetX = 33.43;
		t.anchorOffsetY = 31.43;
		t.height = 62;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "fengye@3x_png";
		t.width = 62;
		t.x = 927.81;
		t.y = 593.1400000000001;
		return t;
	};
	_proto.Return_i = function () {
		var t = new eui.Button();
		this.Return = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 300;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 300;
		t.x = 1265.0000000000002;
		t.y = 735;
		t.skinName = SomeoneRoom$Skin7;
		return t;
	};
	_proto.farmland1_i = function () {
		var t = new eui.Image();
		this.farmland1 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1221.26;
		t.y = 1062.7300000000002;
		return t;
	};
	_proto.farmland2_i = function () {
		var t = new eui.Image();
		this.farmland2 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1148.45;
		t.y = 1098.12;
		return t;
	};
	_proto.farmland3_i = function () {
		var t = new eui.Image();
		this.farmland3 = t;
		t.height = 75;
		t.rotation = 359.92;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1285.15;
		t.y = 1106.68;
		return t;
	};
	_proto.farmland4_i = function () {
		var t = new eui.Image();
		this.farmland4 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1075.13;
		t.y = 1132.5;
		return t;
	};
	_proto.farmland5_i = function () {
		var t = new eui.Image();
		this.farmland5 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1216.34;
		t.y = 1142.59;
		return t;
	};
	_proto.farmland6_i = function () {
		var t = new eui.Image();
		this.farmland6 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1352.62;
		t.y = 1148.18;
		return t;
	};
	_proto.farmland7_i = function () {
		var t = new eui.Image();
		this.farmland7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75;
		t.rotation = 359.92;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1138.76;
		t.y = 1175.41;
		return t;
	};
	_proto.farmland8_i = function () {
		var t = new eui.Image();
		this.farmland8 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1285.01;
		t.y = 1183.92;
		return t;
	};
	_proto.farmland9_i = function () {
		var t = new eui.Image();
		this.farmland9 = t;
		t.height = 75;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1208.35;
		t.y = 1216.66;
		return t;
	};
	_proto.LandBuy_i = function () {
		var t = new eui.Image();
		this.LandBuy = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = -857.27;
		t.pixelHitTest = false;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "zhongzhi@2x(1)_png";
		t.width = -1233.03;
		t.x = 1221.54;
		t.y = 1200.89;
		return t;
	};
	_proto.land1_i = function () {
		var t = new eui.Image();
		this.land1 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 500;
		return t;
	};
	_proto.land2_i = function () {
		var t = new eui.Image();
		this.land2 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 500;
		return t;
	};
	_proto.land3_i = function () {
		var t = new eui.Image();
		this.land3 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 500;
		return t;
	};
	_proto.land4_i = function () {
		var t = new eui.Image();
		this.land4 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 600;
		return t;
	};
	_proto.land5_i = function () {
		var t = new eui.Image();
		this.land5 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 600;
		return t;
	};
	_proto.land6_i = function () {
		var t = new eui.Image();
		this.land6 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 600;
		return t;
	};
	_proto.land7_i = function () {
		var t = new eui.Image();
		this.land7 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 700;
		return t;
	};
	_proto.land8_i = function () {
		var t = new eui.Image();
		this.land8 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 700;
		return t;
	};
	_proto.land9_i = function () {
		var t = new eui.Image();
		this.land9 = t;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 700;
		return t;
	};
	_proto.rare1_i = function () {
		var t = new eui.Image();
		this.rare1 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 450;
		return t;
	};
	_proto.rare2_i = function () {
		var t = new eui.Image();
		this.rare2 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 450;
		return t;
	};
	_proto.rare3_i = function () {
		var t = new eui.Image();
		this.rare3 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 450;
		return t;
	};
	_proto.rare4_i = function () {
		var t = new eui.Image();
		this.rare4 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 550;
		return t;
	};
	_proto.rare5_i = function () {
		var t = new eui.Image();
		this.rare5 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 550;
		return t;
	};
	_proto.rare6_i = function () {
		var t = new eui.Image();
		this.rare6 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 550;
		return t;
	};
	_proto.rare7_i = function () {
		var t = new eui.Image();
		this.rare7 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 650;
		return t;
	};
	_proto.rare8_i = function () {
		var t = new eui.Image();
		this.rare8 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 650;
		return t;
	};
	_proto.rare9_i = function () {
		var t = new eui.Image();
		this.rare9 = t;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 650;
		return t;
	};
	_proto.ripe1_i = function () {
		var t = new eui.Image();
		this.ripe1 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 400;
		return t;
	};
	_proto.ripe2_i = function () {
		var t = new eui.Image();
		this.ripe2 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 400;
		return t;
	};
	_proto.ripe3_i = function () {
		var t = new eui.Image();
		this.ripe3 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 400;
		return t;
	};
	_proto.ripe4_i = function () {
		var t = new eui.Image();
		this.ripe4 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 500;
		return t;
	};
	_proto.ripe5_i = function () {
		var t = new eui.Image();
		this.ripe5 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 500;
		return t;
	};
	_proto.ripe6_i = function () {
		var t = new eui.Image();
		this.ripe6 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 500;
		return t;
	};
	_proto.ripe7_i = function () {
		var t = new eui.Image();
		this.ripe7 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 600;
		return t;
	};
	_proto.ripe8_i = function () {
		var t = new eui.Image();
		this.ripe8 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 600;
		return t;
	};
	_proto.ripe9_i = function () {
		var t = new eui.Image();
		this.ripe9 = t;
		t.height = 200;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 600;
		return t;
	};
	_proto.dogbowl0_i = function () {
		var t = new eui.Image();
		this.dogbowl0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "gouliang 3x_png";
		t.visible = false;
		t.width = 88;
		t.x = 1072;
		t.y = 1322.0000000000002;
		return t;
	};
	_proto.zhai9_i = function () {
		var t = new eui.Image();
		this.zhai9 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 300;
		return t;
	};
	_proto.zhai10_i = function () {
		var t = new eui.Image();
		this.zhai10 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 300;
		return t;
	};
	_proto.zhai11_i = function () {
		var t = new eui.Image();
		this.zhai11 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415.00000000000006;
		t.y = 300;
		return t;
	};
	_proto.zhai12_i = function () {
		var t = new eui.Image();
		this.zhai12 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 400;
		return t;
	};
	_proto.zhai13_i = function () {
		var t = new eui.Image();
		this.zhai13 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 400;
		return t;
	};
	_proto.zhai14_i = function () {
		var t = new eui.Image();
		this.zhai14 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415.00000000000006;
		t.y = 400;
		return t;
	};
	_proto.zhai15_i = function () {
		var t = new eui.Image();
		this.zhai15 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 500;
		return t;
	};
	_proto.zhai16_i = function () {
		var t = new eui.Image();
		this.zhai16 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 500;
		return t;
	};
	_proto.zhai17_i = function () {
		var t = new eui.Image();
		this.zhai17 = t;
		t.height = 70;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415.00000000000006;
		t.y = 500;
		return t;
	};
	_proto.IndianArrowheads_i = function () {
		var t = new eui.Button();
		this.IndianArrowheads = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 400;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.touchChildren = true;
		t.width = 500;
		t.x = 500;
		t.y = 750;
		t.skinName = SomeoneRoom$Skin8;
		return t;
	};
	_proto._Image3_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 150;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "yutang@3x_png";
		t.width = 150;
		t.x = 545;
		t.y = 1350;
		return t;
	};
	_proto.rail_i = function () {
		var t = new eui.Image();
		this.rail = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 356;
		t.pixelHitTest = true;
		t.rotation = 360;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "liba@3x_png";
		t.width = 506;
		t.x = 1010;
		t.y = 970.0000000000001;
		return t;
	};
	_proto.dog_i = function () {
		var t = new eui.Image();
		this.dog = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 80;
		t.rotation = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "erha@3x_png";
		t.width = 80;
		t.x = 996.7200000000001;
		t.y = 1288.06;
		return t;
	};
	_proto._Image4_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "fengche@3x_png";
		t.width = 74;
		t.x = 410.00000000000006;
		t.y = 585.14;
		return t;
	};
	_proto.rotate1_i = function () {
		var t = new eui.Image();
		this.rotate1 = t;
		t.anchorOffsetX = 33.43;
		t.anchorOffsetY = 31.43;
		t.height = 62;
		t.rotation = 45;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "fengye@3x_png";
		t.width = 62;
		t.x = 459.8257864376269;
		t.y = 570.7070541722406;
		return t;
	};
	_proto._Image5_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 300;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "2@3x_png";
		t.width = 1800;
		t.x = 0;
		t.y = 356;
		return t;
	};
	_proto.NameBackground0_i = function () {
		var t = new eui.Image();
		this.NameBackground0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 45;
		t.source = "starshine_png";
		t.width = 83;
		t.x = 34;
		t.y = 165;
		return t;
	};
	_proto.LV0_i = function () {
		var t = new eui.Button();
		this.LV0 = t;
		t.label = "等级";
		t.touchEnabled = false;
		t.x = 230;
		t.y = 23;
		return t;
	};
	_proto.Name0_i = function () {
		var t = new eui.Label();
		this.Name0 = t;
		t.maxChars = 8;
		t.text = "Label";
		t.x = 34;
		t.y = 174;
		return t;
	};
	_proto.HeadPortrait0_i = function () {
		var t = new eui.Image();
		this.HeadPortrait0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 140;
		t.source = "white2_png";
		t.width = 148;
		t.x = 12;
		t.y = 14;
		return t;
	};
	_proto.Money0_i = function () {
		var t = new eui.Button();
		this.Money0 = t;
		t.anchorOffsetX = 0;
		t.label = "100";
		t.touchEnabled = false;
		t.width = 194;
		t.x = 360;
		t.y = 23;
		return t;
	};
	_proto._Button1_i = function () {
		var t = new eui.Button();
		t.label = "";
		t.x = 620;
		t.y = 25;
		t.skinName = SomeoneRoom$Skin9;
		return t;
	};
	return SomeoneRoom;
})(eui.Skin);generateEUI.paths['resource/eui_skins/TextInputSkin.exml'] = window.skins.TextInputSkin = (function (_super) {
	__extends(TextInputSkin, _super);
	function TextInputSkin() {
		_super.call(this);
		this.skinParts = ["textDisplay","promptDisplay"];
		
		this.minHeight = 40;
		this.minWidth = 300;
		this.elementsContent = [this.textDisplay_i()];
		this.promptDisplay_i();
		
		this.states = [
			new eui.State ("normal",
				[
				])
			,
			new eui.State ("disabled",
				[
					new eui.SetProperty("textDisplay","textColor",0xff0000)
				])
			,
			new eui.State ("normalWithPrompt",
				[
					new eui.AddItems("promptDisplay","",1,"")
				])
			,
			new eui.State ("disabledWithPrompt",
				[
					new eui.AddItems("promptDisplay","",1,"")
				])
		];
	}
	var _proto = TextInputSkin.prototype;

	_proto.textDisplay_i = function () {
		var t = new eui.EditableText();
		this.textDisplay = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = "8";
		t.left = "12";
		t.multiline = true;
		t.right = "11";
		t.size = 10;
		t.text = "";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.top = "9";
		t.verticalAlign = "top";
		return t;
	};
	_proto.promptDisplay_i = function () {
		var t = new eui.Label();
		this.promptDisplay = t;
		t.height = 24;
		t.left = 9;
		t.right = 11;
		t.size = 10;
		t.text = "";
		t.textColor = 0xa9a9a9;
		t.top = 12;
		t.touchEnabled = false;
		t.percentWidth = 100;
		return t;
	};
	return TextInputSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/TheFarmLobby.exml'] = window.TheFarmLobbySkin = (function (_super) {
	__extends(TheFarmLobbySkin, _super);
	var TheFarmLobbySkin$Skin10 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin10, _super);
		function TheFarmLobbySkin$Skin10() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin10.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fangzi@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin10;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin11 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin11, _super);
		function TheFarmLobbySkin$Skin11() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin11.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "wabao@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin11;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin12 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin12, _super);
		function TheFarmLobbySkin$Skin12() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin12.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jXX@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin12;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin13 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin13, _super);
		function TheFarmLobbySkin$Skin13() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin13.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "shezhi@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin13;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin14 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin14, _super);
		function TheFarmLobbySkin$Skin14() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin14.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "haoyou@3x(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin14;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin15 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin15, _super);
		function TheFarmLobbySkin$Skin15() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin15.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "bangzhu@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin15;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin16 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin16, _super);
		function TheFarmLobbySkin$Skin16() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin16.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "renwu@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin16;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin17 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin17, _super);
		function TheFarmLobbySkin$Skin17() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin17.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "daojuduihuan@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin17;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin18 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin18, _super);
		function TheFarmLobbySkin$Skin18() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin18.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "quxiao@3x(2)2_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin18;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin19 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin19, _super);
		function TheFarmLobbySkin$Skin19() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin19.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin19;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin20 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin20, _super);
		function TheFarmLobbySkin$Skin20() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin20.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin20;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin21 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin21, _super);
		function TheFarmLobbySkin$Skin21() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin21.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "header_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin21;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin22 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin22, _super);
		function TheFarmLobbySkin$Skin22() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin22.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "shifei@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin22;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin23 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin23, _super);
		function TheFarmLobbySkin$Skin23() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin23.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jiaoshui@x3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin23;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin24 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin24, _super);
		function TheFarmLobbySkin$Skin24() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin24.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "chucao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin24;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin25 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin25, _super);
		function TheFarmLobbySkin$Skin25() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin25.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "zhuochong@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin25;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin26 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin26, _super);
		function TheFarmLobbySkin$Skin26() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin26.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "oneerbeifeiliao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin26;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin27 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin27, _super);
		function TheFarmLobbySkin$Skin27() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin27.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "twosanbeifeiliao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin27;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin28 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin28, _super);
		function TheFarmLobbySkin$Skin28() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin28.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "threewubeifeiliao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin28;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin29 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin29, _super);
		function TheFarmLobbySkin$Skin29() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90),
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin29.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin29;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin30 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin30, _super);
		function TheFarmLobbySkin$Skin30() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin30.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin30;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin31 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin31, _super);
		function TheFarmLobbySkin$Skin31() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","baocun@3x(1)_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin31.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "baocun@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin31;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin32 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin32, _super);
		function TheFarmLobbySkin$Skin32() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin32.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "chakan@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin32;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin33 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin33, _super);
		function TheFarmLobbySkin$Skin33() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin33.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "feiliao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin33;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin34 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin34, _super);
		function TheFarmLobbySkin$Skin34() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin34.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fangfa_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin34;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin35 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin35, _super);
		function TheFarmLobbySkin$Skin35() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin35.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin35;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin36 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin36, _super);
		function TheFarmLobbySkin$Skin36() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","quedingZS@3x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin36.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "quedingZS@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin36;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin37 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin37, _super);
		function TheFarmLobbySkin$Skin37() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin37.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin37;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin38 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin38, _super);
		function TheFarmLobbySkin$Skin38() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin38.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jinchutou@2x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin38;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin39 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin39, _super);
		function TheFarmLobbySkin$Skin39() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin39.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "julu@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin39;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin40 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin40, _super);
		function TheFarmLobbySkin$Skin40() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin40.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin40;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin41 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin41, _super);
		function TheFarmLobbySkin$Skin41() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin41.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin41;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin42 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin42, _super);
		function TheFarmLobbySkin$Skin42() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin42.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin42;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin43 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin43, _super);
		function TheFarmLobbySkin$Skin43() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin43.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin43;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin44 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin44, _super);
		function TheFarmLobbySkin$Skin44() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin44.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin44;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin45 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin45, _super);
		function TheFarmLobbySkin$Skin45() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin45.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin45;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin46 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin46, _super);
		function TheFarmLobbySkin$Skin46() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin46.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin46;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin47 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin47, _super);
		function TheFarmLobbySkin$Skin47() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin47.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin47;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin48 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin48, _super);
		function TheFarmLobbySkin$Skin48() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin48.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "daojujulu@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin48;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin49 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin49, _super);
		function TheFarmLobbySkin$Skin49() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin49.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin49;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin50 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin50, _super);
		function TheFarmLobbySkin$Skin50() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin50.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jian_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin50;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin51 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin51, _super);
		function TheFarmLobbySkin$Skin51() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin51.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jia_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin51;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin52 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin52, _super);
		function TheFarmLobbySkin$Skin52() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","zengsong_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin52.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "zengsong_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin52;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin53 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin53, _super);
		function TheFarmLobbySkin$Skin53() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","goumai(1)_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin53.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "goumai(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin53;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin54 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin54, _super);
		function TheFarmLobbySkin$Skin54() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin54.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin54;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin55 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin55, _super);
		function TheFarmLobbySkin$Skin55() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin55.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jian_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin55;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin56 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin56, _super);
		function TheFarmLobbySkin$Skin56() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin56.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jia_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin56;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin57 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin57, _super);
		function TheFarmLobbySkin$Skin57() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","zengsong_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin57.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "zengsong_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin57;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin58 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin58, _super);
		function TheFarmLobbySkin$Skin58() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","goumai(1)_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin58.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "goumai(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin58;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin59 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin59, _super);
		function TheFarmLobbySkin$Skin59() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin59.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin59;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin60 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin60, _super);
		function TheFarmLobbySkin$Skin60() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","quedingDJGMQD@3x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin60.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "quedingDJGMQD@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin60;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin61 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin61, _super);
		function TheFarmLobbySkin$Skin61() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin61.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin61;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin62 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin62, _super);
		function TheFarmLobbySkin$Skin62() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin62.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin62;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin63 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin63, _super);
		function TheFarmLobbySkin$Skin63() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin63.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "daojujulu@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin63;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin64 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin64, _super);
		function TheFarmLobbySkin$Skin64() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin64.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jian_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin64;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin65 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin65, _super);
		function TheFarmLobbySkin$Skin65() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin65.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "jia_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin65;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin66 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin66, _super);
		function TheFarmLobbySkin$Skin66() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","zengsong_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin66.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "zengsong_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin66;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin67 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin67, _super);
		function TheFarmLobbySkin$Skin67() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","goumai(1)_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin67.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "goumai(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin67;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin68 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin68, _super);
		function TheFarmLobbySkin$Skin68() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin68.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin68;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin69 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin69, _super);
		function TheFarmLobbySkin$Skin69() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin69.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "duihuanjilu@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin69;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin70 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin70, _super);
		function TheFarmLobbySkin$Skin70() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","duihuanB@3x(1)_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin70.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "duihuanK@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin70;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin71 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin71, _super);
		function TheFarmLobbySkin$Skin71() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","duihuanB@3x(1)_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin71.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "duihuanK@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin71;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin72 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin72, _super);
		function TheFarmLobbySkin$Skin72() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","duihuanB@3x(1)_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin72.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "duihuanK@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin72;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin73 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin73, _super);
		function TheFarmLobbySkin$Skin73() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin73.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin73;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin74 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin74, _super);
		function TheFarmLobbySkin$Skin74() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin74.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin74;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin75 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin75, _super);
		function TheFarmLobbySkin$Skin75() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin75.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "yiwen@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin75;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin76 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin76, _super);
		function TheFarmLobbySkin$Skin76() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin76.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin76;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin77 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin77, _super);
		function TheFarmLobbySkin$Skin77() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin77.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin77;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin78 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin78, _super);
		function TheFarmLobbySkin$Skin78() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin78.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin78;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin79 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin79, _super);
		function TheFarmLobbySkin$Skin79() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin79.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin79;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin80 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin80, _super);
		function TheFarmLobbySkin$Skin80() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin80.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin80;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin81 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin81, _super);
		function TheFarmLobbySkin$Skin81() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin81.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin81;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin82 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin82, _super);
		function TheFarmLobbySkin$Skin82() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin82.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin82;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin83 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin83, _super);
		function TheFarmLobbySkin$Skin83() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin83.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin83;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin84 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin84, _super);
		function TheFarmLobbySkin$Skin84() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin84.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin84;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin85 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin85, _super);
		function TheFarmLobbySkin$Skin85() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin85.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin85;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin86 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin86, _super);
		function TheFarmLobbySkin$Skin86() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin86.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "tianjia@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin86;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin87 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin87, _super);
		function TheFarmLobbySkin$Skin87() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin87.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin87;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin88 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin88, _super);
		function TheFarmLobbySkin$Skin88() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin88.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin88;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin89 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin89, _super);
		function TheFarmLobbySkin$Skin89() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin89.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "quxiao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin89;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin90 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin90, _super);
		function TheFarmLobbySkin$Skin90() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin90.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin90;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin91 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin91, _super);
		function TheFarmLobbySkin$Skin91() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin91.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin91;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin92 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin92, _super);
		function TheFarmLobbySkin$Skin92() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin92.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "quxiao@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin92;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin93 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin93, _super);
		function TheFarmLobbySkin$Skin93() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin93.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin93;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin94 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin94, _super);
		function TheFarmLobbySkin$Skin94() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin94.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "BKjuxing@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin94;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin95 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin95, _super);
		function TheFarmLobbySkin$Skin95() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin95.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "biaoqing_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin95;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin96 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin96, _super);
		function TheFarmLobbySkin$Skin96() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin96.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "fasong@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin96;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin97 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin97, _super);
		function TheFarmLobbySkin$Skin97() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin97.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin97;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin98 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin98, _super);
		function TheFarmLobbySkin$Skin98() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","queding@3x_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin98.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "queding@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin98;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin99 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin99, _super);
		function TheFarmLobbySkin$Skin99() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin99.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin99;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin100 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin100, _super);
		function TheFarmLobbySkin$Skin100() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin100.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin100;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin101 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin101, _super);
		function TheFarmLobbySkin$Skin101() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin101.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin101;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin102 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin102, _super);
		function TheFarmLobbySkin$Skin102() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin102.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin102;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin103 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin103, _super);
		function TheFarmLobbySkin$Skin103() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin103.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin103;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin104 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin104, _super);
		function TheFarmLobbySkin$Skin104() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin104.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin104;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin105 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin105, _super);
		function TheFarmLobbySkin$Skin105() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin105.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin105;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin106 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin106, _super);
		function TheFarmLobbySkin$Skin106() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin106.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin106;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin107 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin107, _super);
		function TheFarmLobbySkin$Skin107() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin107.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin107;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin108 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin108, _super);
		function TheFarmLobbySkin$Skin108() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","LQDJ_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin108.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "LQL@3x_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin108;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin109 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin109, _super);
		function TheFarmLobbySkin$Skin109() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
						new eui.SetProperty("_Image1","source","x_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin109.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "x_png";
			t.verticalCenter = 0;
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin109;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin110 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin110, _super);
		function TheFarmLobbySkin$Skin110() {
			_super.call(this);
			this.skinParts = ["labelDisplay"];
			
			this.elementsContent = [this._Image1_i(),this.labelDisplay_i()];
			this.states = [
				new eui.State ("up",
					[
					])
				,
				new eui.State ("down",
					[
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin110.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "queding(1)_png";
			t.percentWidth = 100;
			return t;
		};
		_proto.labelDisplay_i = function () {
			var t = new eui.Label();
			this.labelDisplay = t;
			t.horizontalCenter = 0;
			t.verticalCenter = 0;
			return t;
		};
		return TheFarmLobbySkin$Skin110;
	})(eui.Skin);

	function TheFarmLobbySkin() {
		_super.call(this);
		this.skinParts = ["BG","rotate0","Return","farmland1","farmland2","farmland3","farmland4","farmland5","farmland6","farmland7","farmland8","farmland9","IndianArrowheads","rail","gouyidong","rail0","land1","land2","land3","land4","land5","land6","land7","land8","land9","rare1","rare2","rare3","rare4","rare5","rare6","rare7","rare8","rare9","ripe1","ripe2","ripe3","ripe4","ripe5","ripe6","ripe7","ripe8","ripe9","cover1","cover2","cover3","cover4","cover5","cover6","cover7","cover8","cover9","LandBuy","dogbowl0","zhai1","zhai2","zhai3","zhai4","zhai5","zhai6","zhai7","zhai8","zhai9","kz1","kz2","kz3","kz4","kz5","kz6","kz7","kz8","kz9","dog","rotate1","CZ1","CZ2","CZ3","CZ4","CZ5","CZ6","CZ7","CZ8","CZ9","CD1","CD2","CD3","CD4","CD5","CD6","CD7","CD8","CD9","rollxd","roll","NameBackground","Name","HeadPortrait","daojukuang2","daojukuang1","daoju1","daoju2","LV","Install","Friend","Money","Help","Task","FHZSJBJ","FHZSJ","FHZSJBJ0","FHZSJ0","DTGDXX","Prop","WBJL_S","WBDJLQ","WBJL_text","WBJLJM","wabao","jlda","dim0","kjts_close","KJTS_text","KJTS_close","KJTS","dim1","ZSTZ","succeed_close0","shut0","feiliaotishiyu","dark","hint_text","hint_X","dim","quit","watering","weeding","deinsectization","RipeTime1","RipeTime2","RipeTime3","RipeTime4","RipeTime5","RipeTime6","RipeTime7","RipeTime8","RipeTime9","manure1","manure2","manure3","manure1_bj","manure2_bj","manure3_bj","manure1_text","manure2_text","manure3_text","AggregateScheduling1","Scheduling1","Scheduling_Number1","AggregateScheduling9","Scheduling9","Scheduling_Number9","AggregateScheduling2","Scheduling2","Scheduling_Number2","AggregateScheduling3","Scheduling3","Scheduling_Number3","AggregateScheduling4","Scheduling4","Scheduling_Number4","AggregateScheduling5","Scheduling5","Scheduling_Number5","AggregateScheduling6","Scheduling6","Scheduling_Number6","AggregateScheduling7","Scheduling7","Scheduling_Number7","AggregateScheduling8","Scheduling8","Scheduling_Number8","SJ9","SJ1","SJ2","SJ3","SJ4","SJ5","SJ6","SJ7","SJ8","condition","Music","SoundEffect","Options_close","Options","MyCenter_close","ChangeName","modification","CurrentTitleLevel","detail","TheCurrentMoney","TotalMoney","TheNextLevel","TotalMoneyTow","TheNextLevelMoney","fertilizer","plan","schedule","Money_center","MyCenter","Designation_close","CHQD","Designation","TheIndianArrowheads_close","Goldhoe","IndianArrowheadsNumber","Indian_arrowheads_record1","platinumhoe","diamondhoe","platinumhoe_zhi","diamondhoe_zhi","gundong1","wenzi1","inform_frame","inform_close","inform","Log_message","Indian_arrowheads_record2_close","Indian_arrowheads_record2","WBCS__close","WBCS","WBXX_zi","WBXX__close","WBXX","TheIndianArrowheads","ForRecord_close","money","RecordBox","RecordStrip","GoAndSee","ForRecord","BrowserHelpMenu_close","RaiseMoney","Water","Seed","PetPig","HelpText","HelpText0","HelpText1","HelpText2","BrowserHelpMenu","PropsFor_close","ContentProps","Conversion","warehouse","prop_money","PropsRecord","consume","function","seed","petattack","gundong0","wenzi0","cry","WarehouseGoods","ConsumeGoods","ConsumeGoods_close","SPTP","buy_text","ConsumeGoods_Subtract","ConsumeGoods_Plus","PurchaseQuantity","presented","purchase0","price","ConsumeGoods_Buy","ConsumeGoods_close_zhongzi","SPTP_zhongzi","buy_text_zhongzi","ConsumeGoods_Subtract_zhongzi","ConsumeGoods_Plus_zhongzi","PurchaseQuantity_zhongzi","presented_zhongzi","purchase_zhongzi","price_zhongzi","ConsumeGoods_zhongzi","ConsumeGoods_close0","DJSYJMSPTB","buy_text0","purchase1","shuliang","ConsumeGoods_Buy0","PropsOrder_close","OrderForm","Indent","PropsOrder","PropsFor_close0","prop_money0","PropsRecord0","ConsumeGoods_Subtract_zhongzi0","ConsumeGoods_Plus_zhongzi0","PurchaseQuantity_zhongzi0","presented_zhongzi0","purchase_zhongzi0","libaoXQ","Presented_close","PresentedJM","Presented","zhifu_bj","weixin1","zhifubao1","weixin","zhifubao","zhifu","PropsClass","Change","tu1","tu2","tu3","secret1","secret2","secret3","gundong2","wenzi2","conversion_presenter_1","conversion_presenter_2","conversion_presenter_3","Conversion_money","Modellpause","Bill","All","Obligation","ForShipping","HasBeenShipped","indent_particulars","Indent0","MyOrder","DiscussX","Discuss","ChangeTheOrder_close","exchange","ChangeTheOrder","Xtu","Select_category_close","Xname","Xshu","tishi","Select_category","receiver_address","receiver_name","address_close","receiver_number","address_yes","address","succeed_close","deficiency_text","shut1","deficiency","shut2","succeed","tishi_text","shut3","Tishi","discuss_text","discuss_tu","discuss_yes","discuss_close","discuss","hint3_colse","hint3_yes","hint3","ConversionGoods","commodity","Best","Attention","Pick","REGISTRATION","PrivateLetter","AddFriend","Parameters","friend_money","friend_close","figure","MeetPeople","tianjia_close","tianjia_text","tianjia_quxiao","tianjia_queding","tianjia","shanchu_close","shanchu_quxiao","shanchu_queding","shanchu_text","shanchu","PeopleChat_text","PeopleChat","ChatToolsw","ChatTools","ChatFrame","ChatIcon","Send","Expression_bar","SQBJ","FriendsBar1","FriendsBar0","Chitchat","ApplyFor","FindMyPhone_close","PhoneNumber","Find","PlayerList","FindMyPhone","FriendSet_close","MobileSearch","RecommendationFriend","FriendSet","friend","TASK_close","tupian0","tupian1","tupian2","tupian3","tupian4","tupian5","Task1","HebdomadTask1","QDRW","HYRW","WBRW","STRW","HLBZJ1","HLBZJ0","HLBZJ2","DJJJMP","DJJJMP_neirou","jieshaojiemian","TASK","DeadlineTASK_close","Task2","HebdomadTask2","tp1","DJLQ","QDYC","ZZYCZW","day","DrillOne0","TaskOne0","TaskThree0","DrillThree0","tp2","SQYCZW","JCCGYC","DJLQ0","day0","TaskThree1","TaskThree2","TaskThree3","DJSZPM","GMYKZZ","JCCGSC","DrillThree1","DrillThree2","DrillThree3","tp4","tp3","DJLQ1","day1","leij","TaskThree6","DrillTow2","DrillTow0","tp6","LJWBSC","LJSQHLSC","tp5","DJLQ2","day2","jiao","jiao0","jiao1","TJSGHY","WYLCG","SYFLLC","DrillTow3","DrillTow1","DrillTow4","tp8","tp0","tp7","DJLQ3","day3","DrillOne4","DJSZZN","SQSCHYDHL","WBWC","SQWCHL","DrillOne1","DrillOne2","TaskOne4","TaskOne1","TaskOne2","TaskThree4","DrillThree4","tp11","tp10","tp9","DJLQ4","day4","DrillOne5","TaskOne5","GMYZG","JCCLJSSC","WBLC","SQLCHL","SQHYHLBGESG","TaskThree5","TaskThree7","TaskThree8","TaskThree9","DrillThree5","DrillThree6","DrillThree7","DrillThree8","tp14","tp15","tp16","tp13","tp12","DJLQ5","day5","DJJJMP0","DJJJMP_neirou0","jieshaojiemian0","OneDays","TowDays","ThreeDays","FourDays","FiveDays","SixDays","SevenDays","DeadlineTASK","receiver_address0","receiver_name0","address_close0","receiver_number0","address_yes0","address0_DT","TISHIYUJU","tishixinxi"];
		
		this.height = 667;
		this.width = 375;
		this.elementsContent = [this.roll_i(),this.NameBackground_i(),this.Name_i(),this._Image7_i(),this.HeadPortrait_i(),this._Image8_i(),this._Button1_i(),this._Image9_i(),this._Image10_i(),this.daojukuang2_i(),this.daojukuang1_i(),this.daoju1_i(),this.daoju2_i(),this.LV_i(),this.Install_i(),this.Friend_i(),this.Money_i(),this.Help_i(),this.Task_i(),this.FHZSJBJ_i(),this.FHZSJ_i(),this.FHZSJBJ0_i(),this.FHZSJ0_i(),this.DTGDXX_i(),this.Prop_i(),this.wabao_i(),this.jlda_i(),this.KJTS_i(),this.ZSTZ_i(),this.feiliaotishiyu_i(),this.dark_i(),this.hint_text_i(),this.hint_X_i(),this.condition_i(),this.Options_i(),this.MyCenter_i(),this.Designation_i(),this.TheIndianArrowheads_i(),this.ForRecord_i(),this.BrowserHelpMenu_i(),this.commodity_i(),this.friend_i(),this.TASK_i(),this.DeadlineTASK_i(),this.address0_DT_i(),this.TISHIYUJU_i(),this.tishixinxi_i()];
	}
	var _proto = TheFarmLobbySkin.prototype;

	_proto.roll_i = function () {
		var t = new eui.Scroller();
		this.roll = t;
		t.alpha = 1;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.bounces = false;
		t.enabled = true;
		t.left = 0;
		t.right = 0;
		t.throwSpeed = 0.1;
		t.top = 0;
		t.viewport = this.rollxd_i();
		return t;
	};
	_proto.rollxd_i = function () {
		var t = new eui.Group();
		this.rollxd = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 844;
		t.width = 375;
		t.x = 0;
		t.y = -44;
		t.elementsContent = [this.BG_i(),this._Image1_i(),this.rotate0_i(),this.Return_i(),this._Image2_i(),this.farmland1_i(),this.farmland2_i(),this.farmland3_i(),this.farmland4_i(),this.farmland5_i(),this.farmland6_i(),this.farmland7_i(),this.farmland8_i(),this.farmland9_i(),this.IndianArrowheads_i(),this.rail_i(),this.gouyidong_i(),this.rail0_i(),this.land1_i(),this.land2_i(),this.land3_i(),this.land4_i(),this.land5_i(),this.land6_i(),this.land7_i(),this.land8_i(),this.land9_i(),this.rare1_i(),this.rare2_i(),this.rare3_i(),this.rare4_i(),this.rare5_i(),this.rare6_i(),this.rare7_i(),this.rare8_i(),this.rare9_i(),this.ripe1_i(),this.ripe2_i(),this.ripe3_i(),this.ripe4_i(),this.ripe5_i(),this.ripe6_i(),this.ripe7_i(),this.ripe8_i(),this.ripe9_i(),this.cover1_i(),this.cover2_i(),this.cover3_i(),this.cover4_i(),this.cover5_i(),this.cover6_i(),this.cover7_i(),this.cover8_i(),this.cover9_i(),this.LandBuy_i(),this.dogbowl0_i(),this.zhai1_i(),this.zhai2_i(),this.zhai3_i(),this.zhai4_i(),this.zhai5_i(),this.zhai6_i(),this.zhai7_i(),this.zhai8_i(),this.zhai9_i(),this.kz1_i(),this.kz2_i(),this.kz3_i(),this.kz4_i(),this.kz5_i(),this.kz6_i(),this.kz7_i(),this.kz8_i(),this.kz9_i(),this._Image3_i(),this.dog_i(),this._Image4_i(),this.rotate1_i(),this._Image5_i(),this._Image6_i(),this.CZ1_i(),this.CZ2_i(),this.CZ3_i(),this.CZ4_i(),this.CZ5_i(),this.CZ6_i(),this.CZ7_i(),this.CZ8_i(),this.CZ9_i(),this.CD1_i(),this.CD2_i(),this.CD3_i(),this.CD4_i(),this.CD5_i(),this.CD6_i(),this.CD7_i(),this.CD8_i(),this.CD9_i()];
		return t;
	};
	_proto.BG_i = function () {
		var t = new eui.Image();
		this.BG = t;
		t.height = 850;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing@2x(1)_png";
		t.width = 900;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 44;
		t.source = "fengche@3x_png";
		t.width = 35;
		t.x = 476;
		t.y = 300;
		return t;
	};
	_proto.rotate0_i = function () {
		var t = new eui.Image();
		this.rotate0 = t;
		t.anchorOffsetX = 19.39;
		t.anchorOffsetY = 14.53;
		t.height = 29;
		t.source = "fengye@3x_png";
		t.width = 35;
		t.x = 499.39;
		t.y = 310.34;
		return t;
	};
	_proto.Return_i = function () {
		var t = new eui.Button();
		this.Return = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 202;
		t.label = "";
		t.width = 197;
		t.x = 659;
		t.y = 311.5;
		t.skinName = TheFarmLobbySkin$Skin10;
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 167;
		t.source = "BJCD@3x_png";
		t.width = 287;
		t.x = 444;
		t.y = 465;
		return t;
	};
	_proto.farmland1_i = function () {
		var t = new eui.Image();
		this.farmland1 = t;
		t.height = 42;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 78;
		t.x = 546;
		t.y = 471;
		return t;
	};
	_proto.farmland2_i = function () {
		var t = new eui.Image();
		this.farmland2 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 500;
		t.y = 493;
		return t;
	};
	_proto.farmland3_i = function () {
		var t = new eui.Image();
		this.farmland3 = t;
		t.height = 42;
		t.rotation = 359.92;
		t.source = "weizhongzhi_png";
		t.width = 83;
		t.x = 592;
		t.y = 496;
		return t;
	};
	_proto.farmland4_i = function () {
		var t = new eui.Image();
		this.farmland4 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 453;
		t.y = 521;
		return t;
	};
	_proto.farmland5_i = function () {
		var t = new eui.Image();
		this.farmland5 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 547;
		t.y = 518;
		return t;
	};
	_proto.farmland6_i = function () {
		var t = new eui.Image();
		this.farmland6 = t;
		t.height = 47;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 86;
		t.x = 639;
		t.y = 521;
		return t;
	};
	_proto.farmland7_i = function () {
		var t = new eui.Image();
		this.farmland7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 51;
		t.rotation = 359.92;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 499;
		t.y = 545;
		return t;
	};
	_proto.farmland8_i = function () {
		var t = new eui.Image();
		this.farmland8 = t;
		t.height = 45;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 83;
		t.x = 592;
		t.y = 547;
		return t;
	};
	_proto.farmland9_i = function () {
		var t = new eui.Image();
		this.farmland9 = t;
		t.height = 51;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 81;
		t.x = 547;
		t.y = 571;
		return t;
	};
	_proto.IndianArrowheads_i = function () {
		var t = new eui.Button();
		this.IndianArrowheads = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 175;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.touchChildren = true;
		t.width = 269;
		t.x = 130;
		t.y = 349;
		t.skinName = TheFarmLobbySkin$Skin11;
		return t;
	};
	_proto.rail_i = function () {
		var t = new eui.Image();
		this.rail = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 248.5;
		t.pixelHitTest = true;
		t.rotation = 0;
		t.source = "libaweilan1@3x_png";
		t.width = 387.5;
		t.x = 389;
		t.y = 414.5;
		return t;
	};
	_proto.gouyidong_i = function () {
		var t = new eui.Group();
		this.gouyidong = t;
		t.height = 850;
		t.touchThrough = true;
		t.width = 900;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.rail0_i = function () {
		var t = new eui.Image();
		this.rail0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 131;
		t.pixelHitTest = true;
		t.rotation = 0;
		t.source = "libaweilan2@3x_png";
		t.width = 378.5;
		t.x = 393;
		t.y = 409;
		return t;
	};
	_proto.land1_i = function () {
		var t = new eui.Image();
		this.land1 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 572;
		t.y = 472;
		return t;
	};
	_proto.land2_i = function () {
		var t = new eui.Image();
		this.land2 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 524.32;
		t.y = 498.33;
		return t;
	};
	_proto.land3_i = function () {
		var t = new eui.Image();
		this.land3 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 617.17;
		t.y = 496.98;
		return t;
	};
	_proto.land4_i = function () {
		var t = new eui.Image();
		this.land4 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 477.02;
		t.y = 528.68;
		return t;
	};
	_proto.land5_i = function () {
		var t = new eui.Image();
		this.land5 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 573.7;
		t.y = 525.33;
		return t;
	};
	_proto.land6_i = function () {
		var t = new eui.Image();
		this.land6 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 670.67;
		t.y = 524.67;
		return t;
	};
	_proto.land7_i = function () {
		var t = new eui.Image();
		this.land7 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 527.04;
		t.y = 552.35;
		return t;
	};
	_proto.land8_i = function () {
		var t = new eui.Image();
		this.land8 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 621.71;
		t.y = 549.71;
		return t;
	};
	_proto.land9_i = function () {
		var t = new eui.Image();
		this.land9 = t;
		t.height = 20;
		t.source = "yaoya@3x_png";
		t.visible = false;
		t.width = 25;
		t.x = 574.5;
		t.y = 574.41;
		return t;
	};
	_proto.rare1_i = function () {
		var t = new eui.Image();
		this.rare1 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 564.67;
		t.y = 451.02;
		return t;
	};
	_proto.rare2_i = function () {
		var t = new eui.Image();
		this.rare2 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 519.37;
		t.y = 475;
		return t;
	};
	_proto.rare3_i = function () {
		var t = new eui.Image();
		this.rare3 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 613.31;
		t.y = 474.33;
		return t;
	};
	_proto.rare4_i = function () {
		var t = new eui.Image();
		this.rare4 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 472.66;
		t.y = 506;
		return t;
	};
	_proto.rare5_i = function () {
		var t = new eui.Image();
		this.rare5 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 570;
		t.y = 504.66;
		return t;
	};
	_proto.rare6_i = function () {
		var t = new eui.Image();
		this.rare6 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 663;
		t.y = 502;
		return t;
	};
	_proto.rare7_i = function () {
		var t = new eui.Image();
		this.rare7 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 520.01;
		t.y = 528.67;
		return t;
	};
	_proto.rare8_i = function () {
		var t = new eui.Image();
		this.rare8 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 617.33;
		t.y = 527.01;
		return t;
	};
	_proto.rare9_i = function () {
		var t = new eui.Image();
		this.rare9 = t;
		t.height = 45;
		t.source = "shengzhang@3x_png";
		t.visible = false;
		t.width = 33;
		t.x = 570.02;
		t.y = 557.01;
		return t;
	};
	_proto.ripe1_i = function () {
		var t = new eui.Image();
		this.ripe1 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 564;
		t.y = 446.34;
		return t;
	};
	_proto.ripe2_i = function () {
		var t = new eui.Image();
		this.ripe2 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 521.32;
		t.y = 469.68;
		return t;
	};
	_proto.ripe3_i = function () {
		var t = new eui.Image();
		this.ripe3 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 612.34;
		t.y = 469.67;
		return t;
	};
	_proto.ripe4_i = function () {
		var t = new eui.Image();
		this.ripe4 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 471.66;
		t.y = 497.68;
		return t;
	};
	_proto.ripe5_i = function () {
		var t = new eui.Image();
		this.ripe5 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 566.34;
		t.y = 498.33;
		return t;
	};
	_proto.ripe6_i = function () {
		var t = new eui.Image();
		this.ripe6 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 660.67;
		t.y = 496.19;
		return t;
	};
	_proto.ripe7_i = function () {
		var t = new eui.Image();
		this.ripe7 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 520.33;
		t.y = 525.66;
		return t;
	};
	_proto.ripe8_i = function () {
		var t = new eui.Image();
		this.ripe8 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 615.33;
		t.y = 521.67;
		return t;
	};
	_proto.ripe9_i = function () {
		var t = new eui.Image();
		this.ripe9 = t;
		t.height = 49;
		t.source = "zuowu@3x_png";
		t.visible = false;
		t.width = 44;
		t.x = 569.34;
		t.y = 548.03;
		return t;
	};
	_proto.cover1_i = function () {
		var t = new eui.Image();
		this.cover1 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 534;
		t.y = 435;
		return t;
	};
	_proto.cover2_i = function () {
		var t = new eui.Image();
		this.cover2 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 492;
		t.y = 457;
		return t;
	};
	_proto.cover3_i = function () {
		var t = new eui.Image();
		this.cover3 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 582;
		t.y = 459;
		return t;
	};
	_proto.cover4_i = function () {
		var t = new eui.Image();
		this.cover4 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 444;
		t.y = 485;
		return t;
	};
	_proto.cover5_i = function () {
		var t = new eui.Image();
		this.cover5 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 539;
		t.y = 486;
		return t;
	};
	_proto.cover6_i = function () {
		var t = new eui.Image();
		this.cover6 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 631;
		t.y = 486;
		return t;
	};
	_proto.cover7_i = function () {
		var t = new eui.Image();
		this.cover7 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 491;
		t.y = 513;
		return t;
	};
	_proto.cover8_i = function () {
		var t = new eui.Image();
		this.cover8 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 585;
		t.y = 510;
		return t;
	};
	_proto.cover9_i = function () {
		var t = new eui.Image();
		this.cover9 = t;
		t.height = 63;
		t.source = "zhezhao@3x_png";
		t.visible = false;
		t.width = 100;
		t.x = 538;
		t.y = 539;
		return t;
	};
	_proto.LandBuy_i = function () {
		var t = new eui.Image();
		this.LandBuy = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 44;
		t.pixelHitTest = false;
		t.source = "zhongzhi@3x_png";
		t.width = 49;
		t.x = 564;
		t.y = 504;
		return t;
	};
	_proto.dogbowl0_i = function () {
		var t = new eui.Image();
		this.dogbowl0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "gouliang@3x_png";
		t.width = 28;
		t.x = 489;
		t.y = 654;
		return t;
	};
	_proto.zhai1_i = function () {
		var t = new eui.Image();
		this.zhai1 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 566.01;
		t.y = 406;
		return t;
	};
	_proto.zhai2_i = function () {
		var t = new eui.Image();
		this.zhai2 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 523.34;
		t.y = 428;
		return t;
	};
	_proto.zhai3_i = function () {
		var t = new eui.Image();
		this.zhai3 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 615.34;
		t.y = 428;
		return t;
	};
	_proto.zhai4_i = function () {
		var t = new eui.Image();
		this.zhai4 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 473.67;
		t.y = 456;
		return t;
	};
	_proto.zhai5_i = function () {
		var t = new eui.Image();
		this.zhai5 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 568;
		t.y = 457;
		return t;
	};
	_proto.zhai6_i = function () {
		var t = new eui.Image();
		this.zhai6 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 663;
		t.y = 454;
		return t;
	};
	_proto.zhai7_i = function () {
		var t = new eui.Image();
		this.zhai7 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 522;
		t.y = 483;
		return t;
	};
	_proto.zhai8_i = function () {
		var t = new eui.Image();
		this.zhai8 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 616;
		t.y = 479;
		return t;
	};
	_proto.zhai9_i = function () {
		var t = new eui.Image();
		this.zhai9 = t;
		t.height = 42;
		t.source = "beijingZZSHSL@3x_png";
		t.visible = false;
		t.width = 45;
		t.x = 570;
		t.y = 507;
		return t;
	};
	_proto.kz1_i = function () {
		var t = new eui.Label();
		this.kz1 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 572;
		t.y = 416;
		return t;
	};
	_proto.kz2_i = function () {
		var t = new eui.Label();
		this.kz2 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 529;
		t.y = 439;
		return t;
	};
	_proto.kz3_i = function () {
		var t = new eui.Label();
		this.kz3 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 622;
		t.y = 439;
		return t;
	};
	_proto.kz4_i = function () {
		var t = new eui.Label();
		this.kz4 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 480;
		t.y = 467;
		return t;
	};
	_proto.kz5_i = function () {
		var t = new eui.Label();
		this.kz5 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 574;
		t.y = 467;
		return t;
	};
	_proto.kz6_i = function () {
		var t = new eui.Label();
		this.kz6 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 668;
		t.y = 466;
		return t;
	};
	_proto.kz7_i = function () {
		var t = new eui.Label();
		this.kz7 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 528;
		t.y = 493;
		return t;
	};
	_proto.kz8_i = function () {
		var t = new eui.Label();
		this.kz8 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 622;
		t.y = 489;
		return t;
	};
	_proto.kz9_i = function () {
		var t = new eui.Label();
		this.kz9 = t;
		t.size = 12;
		t.text = "可摘";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 577;
		t.y = 517;
		return t;
	};
	_proto._Image3_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 60;
		t.source = "yutang@3x_png";
		t.width = 67;
		t.x = 261;
		t.y = 690;
		return t;
	};
	_proto.dog_i = function () {
		var t = new eui.Image();
		this.dog = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 55;
		t.rotation = 20;
		t.scaleX = 1.1;
		t.scaleY = 1.15;
		t.source = "erha@3x_png";
		t.visible = false;
		t.width = 53;
		t.x = 789;
		t.y = 500;
		return t;
	};
	_proto._Image4_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 47;
		t.source = "fengche@3x_png";
		t.width = 39;
		t.x = 206;
		t.y = 291;
		return t;
	};
	_proto.rotate1_i = function () {
		var t = new eui.Image();
		this.rotate1 = t;
		t.anchorOffsetX = 18.31;
		t.anchorOffsetY = 14.7;
		t.height = 29;
		t.rotation = 0;
		t.source = "fengye@3x_png";
		t.width = 33;
		t.x = 231.78;
		t.y = 301.42;
		return t;
	};
	_proto._Image5_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 209;
		t.pixelHitTest = true;
		t.source = "2@3x_png";
		t.visible = false;
		t.width = 900;
		t.x = 4;
		t.y = 241;
		return t;
	};
	_proto._Image6_i = function () {
		var t = new eui.Image();
		t.height = 113;
		t.source = "XFZ@3x_png";
		t.width = 117;
		t.x = 756.5;
		t.y = 418;
		return t;
	};
	_proto.CZ1_i = function () {
		var t = new eui.Image();
		this.CZ1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 585;
		t.y = 473.6;
		return t;
	};
	_proto.CZ2_i = function () {
		var t = new eui.Image();
		this.CZ2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 537;
		t.y = 499.2;
		return t;
	};
	_proto.CZ3_i = function () {
		var t = new eui.Image();
		this.CZ3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 629.4;
		t.y = 498.8;
		return t;
	};
	_proto.CZ4_i = function () {
		var t = new eui.Image();
		this.CZ4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 489.4;
		t.y = 530.4;
		return t;
	};
	_proto.CZ5_i = function () {
		var t = new eui.Image();
		this.CZ5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 587;
		t.y = 526.8;
		return t;
	};
	_proto.CZ6_i = function () {
		var t = new eui.Image();
		this.CZ6 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 683.8;
		t.y = 524.8;
		return t;
	};
	_proto.CZ7_i = function () {
		var t = new eui.Image();
		this.CZ7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 540.2;
		t.y = 553.6;
		return t;
	};
	_proto.CZ8_i = function () {
		var t = new eui.Image();
		this.CZ8 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 636.2;
		t.y = 551.2;
		return t;
	};
	_proto.CZ9_i = function () {
		var t = new eui.Image();
		this.CZ9 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 7;
		t.source = "chongzi@2x_png";
		t.visible = false;
		t.width = 7.8;
		t.x = 588.2;
		t.y = 576;
		return t;
	};
	_proto.CD1_i = function () {
		var t = new eui.Image();
		this.CD1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 588.2;
		t.y = 486.6;
		return t;
	};
	_proto.CD2_i = function () {
		var t = new eui.Image();
		this.CD2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 541;
		t.y = 514.2;
		return t;
	};
	_proto.CD3_i = function () {
		var t = new eui.Image();
		this.CD3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 633.4;
		t.y = 512.6;
		return t;
	};
	_proto.CD4_i = function () {
		var t = new eui.Image();
		this.CD4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 493.8;
		t.y = 543.8;
		return t;
	};
	_proto.CD5_i = function () {
		var t = new eui.Image();
		this.CD5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 588.2;
		t.y = 540.6;
		return t;
	};
	_proto.CD6_i = function () {
		var t = new eui.Image();
		this.CD6 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 685.4;
		t.y = 541;
		return t;
	};
	_proto.CD7_i = function () {
		var t = new eui.Image();
		this.CD7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 541.4;
		t.y = 567.4;
		return t;
	};
	_proto.CD8_i = function () {
		var t = new eui.Image();
		this.CD8 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 636.6;
		t.y = 565.8;
		return t;
	};
	_proto.CD9_i = function () {
		var t = new eui.Image();
		this.CD9 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6;
		t.source = "cao@2x_png";
		t.visible = false;
		t.width = 8.8;
		t.x = 589.8;
		t.y = 590.2;
		return t;
	};
	_proto.NameBackground_i = function () {
		var t = new eui.Image();
		this.NameBackground = t;
		t.alpha = 0.6;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "jjjjjjjxxxxxxx_png";
		t.width = 60;
		t.x = 18.68;
		t.y = 85;
		return t;
	};
	_proto.Name_i = function () {
		var t = new eui.Label();
		this.Name = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "MingLiU";
		t.height = 15;
		t.maxChars = 3;
		t.size = 15;
		t.text = "大萨达";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 60;
		t.x = 18.68;
		t.y = 88;
		return t;
	};
	_proto._Image7_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 28;
		t.source = "gonggaobeijing@3x_png";
		t.width = 207;
		t.x = 86;
		t.y = 46;
		return t;
	};
	_proto.HeadPortrait_i = function () {
		var t = new eui.Image();
		this.HeadPortrait = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 54;
		t.source = "white2_png";
		t.width = 60;
		t.x = 18;
		t.y = 14;
		return t;
	};
	_proto._Image8_i = function () {
		var t = new eui.Image();
		t.height = 64;
		t.pixelHitTest = true;
		t.source = "juxingBK@3x_png";
		t.width = 70;
		t.x = 13;
		t.y = 10;
		return t;
	};
	_proto._Button1_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.label = "";
		t.scaleX = 0.9;
		t.width = 75;
		t.x = 196;
		t.y = 18;
		t.skinName = TheFarmLobbySkin$Skin12;
		return t;
	};
	_proto._Image9_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 27;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 20;
		t.x = 188;
		t.y = 11;
		return t;
	};
	_proto._Image10_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "gengduo@3x_png";
		t.width = 29;
		t.x = 251;
		t.y = 14;
		return t;
	};
	_proto.daojukuang2_i = function () {
		var t = new eui.Image();
		this.daojukuang2 = t;
		t.height = 23;
		t.source = "juxingSXDJKJ@3x_png";
		t.visible = false;
		t.width = 23;
		t.x = 113;
		t.y = 84;
		return t;
	};
	_proto.daojukuang1_i = function () {
		var t = new eui.Image();
		this.daojukuang1 = t;
		t.height = 23;
		t.source = "juxingSXDJKJ@3x_png";
		t.visible = false;
		t.width = 23;
		t.x = 87;
		t.y = 84;
		return t;
	};
	_proto.daoju1_i = function () {
		var t = new eui.Image();
		this.daoju1 = t;
		t.height = 20;
		t.source = "mofachuji@3xCJMFD_png";
		t.visible = false;
		t.width = 20;
		t.x = 88;
		t.y = 86;
		return t;
	};
	_proto.daoju2_i = function () {
		var t = new eui.Image();
		this.daoju2 = t;
		t.height = 20;
		t.source = "goubang@3xDGB_png";
		t.visible = false;
		t.width = 20;
		t.x = 115;
		t.y = 86;
		return t;
	};
	_proto.LV_i = function () {
		var t = new eui.Image();
		this.LV = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "";
		t.width = 68;
		t.x = 92;
		t.y = 16;
		return t;
	};
	_proto.Install_i = function () {
		var t = new eui.Button();
		this.Install = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 43;
		t.label = "";
		t.width = 38;
		t.x = 324;
		t.y = 14;
		t.skinName = TheFarmLobbySkin$Skin13;
		return t;
	};
	_proto.Friend_i = function () {
		var t = new eui.Button();
		this.Friend = t;
		t.anchorOffsetX = 0;
		t.height = 40;
		t.label = "";
		t.width = 44;
		t.x = 24;
		t.y = 180;
		t.skinName = TheFarmLobbySkin$Skin14;
		return t;
	};
	_proto.Money_i = function () {
		var t = new eui.Label();
		this.Money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 26;
		t.size = 13;
		t.text = "1000";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 96;
		t.x = 183;
		t.y = 14;
		return t;
	};
	_proto.Help_i = function () {
		var t = new eui.Button();
		this.Help = t;
		t.anchorOffsetY = 0;
		t.height = 46;
		t.label = "";
		t.width = 37;
		t.x = 324;
		t.y = 67;
		t.skinName = TheFarmLobbySkin$Skin15;
		return t;
	};
	_proto.Task_i = function () {
		var t = new eui.Button();
		this.Task = t;
		t.enabled = true;
		t.height = 50;
		t.label = "";
		t.width = 50;
		t.x = 23;
		t.y = 118;
		t.skinName = TheFarmLobbySkin$Skin16;
		return t;
	};
	_proto.FHZSJBJ_i = function () {
		var t = new eui.Image();
		this.FHZSJBJ = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.rotation = 180;
		t.source = "DJJJMP_png";
		t.visible = false;
		t.width = 132;
		t.x = 146;
		t.y = 128;
		return t;
	};
	_proto.FHZSJ_i = function () {
		var t = new eui.Label();
		this.FHZSJ = t;
		t.size = 11;
		t.text = "截止时间：10月41日8h";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 22;
		t.y = 112;
		return t;
	};
	_proto.FHZSJBJ0_i = function () {
		var t = new eui.Image();
		this.FHZSJBJ0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.rotation = 180;
		t.source = "DJJJMP_png";
		t.visible = false;
		t.width = 132;
		t.x = 176;
		t.y = 129;
		return t;
	};
	_proto.FHZSJ0_i = function () {
		var t = new eui.Label();
		this.FHZSJ0 = t;
		t.size = 11;
		t.text = "截止时间：10月41日8h";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 52;
		t.y = 113;
		return t;
	};
	_proto.DTGDXX_i = function () {
		var t = new eui.Image();
		this.DTGDXX = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "yellow@3x_png";
		t.width = 157;
		t.x = 125;
		t.y = 51;
		return t;
	};
	_proto.Prop_i = function () {
		var t = new eui.Button();
		this.Prop = t;
		t.anchorOffsetX = 0;
		t.height = 50;
		t.label = "";
		t.width = 69;
		t.x = 13;
		t.y = 230;
		t.skinName = TheFarmLobbySkin$Skin17;
		return t;
	};
	_proto.wabao_i = function () {
		var t = new eui.Group();
		this.wabao = t;
		t.bottom = 0;
		t.height = 850;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.touchThrough = true;
		t.width = 900;
		t.elementsContent = [this.WBJLJM_i()];
		return t;
	};
	_proto.WBJLJM_i = function () {
		var t = new eui.Group();
		this.WBJLJM = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.top = 0;
		t.visible = false;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image11_i(),this.WBJL_S_i(),this._Image12_i(),this.WBDJLQ_i(),this._Image13_i(),this.WBJL_text_i()];
		return t;
	};
	_proto._Image11_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto.WBJL_S_i = function () {
		var t = new eui.Image();
		this.WBJL_S = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 31;
		t.source = "quxiao@3x(2)2_png";
		t.width = 31;
		t.x = 172;
		t.y = 465;
		return t;
	};
	_proto._Image12_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 226;
		t.source = "beijingWBJSBJ@3x_png";
		t.width = 204;
		t.x = 85.5;
		t.y = 222.5;
		return t;
	};
	_proto.WBDJLQ_i = function () {
		var t = new eui.Image();
		this.WBDJLQ = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 37.5;
		t.source = "dianjilingqu@3x_png";
		t.width = 124.5;
		t.x = 126;
		t.y = 399;
		return t;
	};
	_proto._Image13_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18.5;
		t.source = "wenziGXNHD@3x_png";
		t.width = 101;
		t.x = 137.5;
		t.y = 255;
		return t;
	};
	_proto.WBJL_text_i = function () {
		var t = new eui.Label();
		this.WBJL_text = t;
		t.size = 16;
		t.text = "Label";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 120;
		t.x = 128;
		t.y = 363.5;
		return t;
	};
	_proto.jlda_i = function () {
		var t = new eui.Label();
		this.jlda = t;
		t.bold = true;
		t.size = 12;
		t.text = "猫**仔摘取了中*起2g葫芦币";
		t.x = 300;
		t.y = 55;
		return t;
	};
	_proto.KJTS_i = function () {
		var t = new eui.Group();
		this.KJTS = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this.dim0_i(),this._Image14_i(),this.kjts_close_i(),this.KJTS_text_i(),this._Image15_i(),this.KJTS_close_i()];
		return t;
	};
	_proto.dim0_i = function () {
		var t = new eui.Image();
		this.dim0 = t;
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image14_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 321;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "DTDLJM1@3x2_png";
		t.width = 249;
		t.x = 63;
		t.y = 142;
		return t;
	};
	_proto.kjts_close_i = function () {
		var t = new eui.Button();
		this.kjts_close = t;
		t.height = 31;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 31;
		t.x = 172;
		t.y = 479;
		t.skinName = TheFarmLobbySkin$Skin18;
		return t;
	};
	_proto.KJTS_text_i = function () {
		var t = new eui.Label();
		this.KJTS_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 144;
		t.size = 14;
		t.text = "    欢迎您成为农品街庄园的用户。";
		t.textColor = 0x000000;
		t.width = 152;
		t.x = 117;
		t.y = 250;
		return t;
	};
	_proto._Image15_i = function () {
		var t = new eui.Image();
		t.height = 35;
		t.source = "tishiDT@3x_png";
		t.width = 64;
		t.x = 162;
		t.y = 189;
		return t;
	};
	_proto.KJTS_close_i = function () {
		var t = new eui.Image();
		this.KJTS_close = t;
		t.height = 36;
		t.source = "mahsangzhongzhi@3x2_png";
		t.width = 91;
		t.x = 147;
		t.y = 399;
		return t;
	};
	_proto.ZSTZ_i = function () {
		var t = new eui.Group();
		this.ZSTZ = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.x = 10;
		t.y = 10;
		t.elementsContent = [this.dim1_i()];
		return t;
	};
	_proto.dim1_i = function () {
		var t = new eui.Image();
		this.dim1 = t;
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.feiliaotishiyu_i = function () {
		var t = new eui.Group();
		this.feiliaotishiyu = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image16_i(),this._Image17_i(),this.succeed_close0_i(),this._Image18_i(),this.shut0_i()];
		return t;
	};
	_proto._Image16_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image17_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 225;
		t.source = "beijingBKD@3x_png";
		t.width = 300;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto.succeed_close0_i = function () {
		var t = new eui.Button();
		this.succeed_close0 = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 307;
		t.y = 207;
		t.skinName = TheFarmLobbySkin$Skin19;
		return t;
	};
	_proto._Image18_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.source = "tishi_png";
		t.width = 204;
		t.x = 87;
		t.y = 200;
		return t;
	};
	_proto.shut0_i = function () {
		var t = new eui.Button();
		this.shut0 = t;
		t.height = 36;
		t.label = "";
		t.width = 76;
		t.x = 150;
		t.y = 356;
		t.skinName = TheFarmLobbySkin$Skin20;
		return t;
	};
	_proto.dark_i = function () {
		var t = new eui.Image();
		this.dark = t;
		t.bottom = 0;
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		return t;
	};
	_proto.hint_text_i = function () {
		var t = new eui.Label();
		this.hint_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.size = 30;
		t.text = "请选择要种植的土地";
		t.visible = false;
		t.x = 56.06;
		t.y = 134;
		return t;
	};
	_proto.hint_X_i = function () {
		var t = new eui.Button();
		this.hint_X = t;
		t.label = "X";
		t.visible = false;
		t.x = 303;
		t.y = 39;
		t.skinName = TheFarmLobbySkin$Skin21;
		return t;
	};
	_proto.condition_i = function () {
		var t = new eui.Group();
		this.condition = t;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.dim_i(),this.quit_i(),this.watering_i(),this.weeding_i(),this.deinsectization_i(),this.RipeTime1_i(),this.RipeTime2_i(),this.RipeTime3_i(),this.RipeTime4_i(),this.RipeTime5_i(),this.RipeTime6_i(),this.RipeTime7_i(),this.RipeTime8_i(),this.RipeTime9_i(),this.manure1_i(),this.manure2_i(),this.manure3_i(),this.manure1_bj_i(),this.manure2_bj_i(),this.manure3_bj_i(),this.manure1_text_i(),this.manure2_text_i(),this.manure3_text_i(),this.AggregateScheduling1_i(),this.Scheduling1_i(),this.Scheduling_Number1_i(),this.AggregateScheduling9_i(),this.Scheduling9_i(),this.Scheduling_Number9_i(),this.AggregateScheduling2_i(),this.Scheduling2_i(),this.Scheduling_Number2_i(),this.AggregateScheduling3_i(),this.Scheduling3_i(),this.Scheduling_Number3_i(),this.AggregateScheduling4_i(),this.Scheduling4_i(),this.Scheduling_Number4_i(),this.AggregateScheduling5_i(),this.Scheduling5_i(),this.Scheduling_Number5_i(),this.AggregateScheduling6_i(),this.Scheduling6_i(),this.Scheduling_Number6_i(),this.AggregateScheduling7_i(),this.Scheduling7_i(),this.Scheduling_Number7_i(),this.AggregateScheduling8_i(),this.Scheduling8_i(),this.Scheduling_Number8_i(),this.SJ9_i(),this.SJ1_i(),this.SJ2_i(),this.SJ3_i(),this.SJ4_i(),this.SJ5_i(),this.SJ6_i(),this.SJ7_i(),this.SJ8_i()];
		return t;
	};
	_proto.dim_i = function () {
		var t = new eui.Image();
		this.dim = t;
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.quit_i = function () {
		var t = new eui.Button();
		this.quit = t;
		t.height = 40;
		t.label = "";
		t.width = 44;
		t.x = 114;
		t.y = 246;
		t.skinName = TheFarmLobbySkin$Skin22;
		return t;
	};
	_proto.watering_i = function () {
		var t = new eui.Button();
		this.watering = t;
		t.height = 40;
		t.label = "";
		t.width = 44;
		t.x = 245;
		t.y = 279.03;
		t.skinName = TheFarmLobbySkin$Skin23;
		return t;
	};
	_proto.weeding_i = function () {
		var t = new eui.Button();
		this.weeding = t;
		t.height = 40;
		t.label = "";
		t.width = 44;
		t.x = 185;
		t.y = 184.66;
		t.skinName = TheFarmLobbySkin$Skin24;
		return t;
	};
	_proto.deinsectization_i = function () {
		var t = new eui.Button();
		this.deinsectization = t;
		t.height = 40;
		t.label = "";
		t.width = 44;
		t.x = 204.4;
		t.y = 340.87;
		t.skinName = TheFarmLobbySkin$Skin25;
		return t;
	};
	_proto.RipeTime1_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.value = 0;
		t.visible = false;
		t.width = 53;
		t.x = 292;
		t.y = 453.58;
		return t;
	};
	_proto.RipeTime2_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 254;
		t.y = 473.58;
		return t;
	};
	_proto.RipeTime3_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 264;
		t.y = 483.58;
		return t;
	};
	_proto.RipeTime4_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 274;
		t.y = 493.58;
		return t;
	};
	_proto.RipeTime5_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 284;
		t.y = 503.58;
		return t;
	};
	_proto.RipeTime6_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime6 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 294;
		t.y = 513.5799999999999;
		return t;
	};
	_proto.RipeTime7_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 304;
		t.y = 523.5799999999999;
		return t;
	};
	_proto.RipeTime8_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime8 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 314;
		t.y = 533.5799999999999;
		return t;
	};
	_proto.RipeTime9_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime9 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.visible = false;
		t.width = 53;
		t.x = 324;
		t.y = 543.5799999999999;
		return t;
	};
	_proto.manure1_i = function () {
		var t = new eui.Button();
		this.manure1 = t;
		t.height = 30;
		t.label = "";
		t.visible = false;
		t.width = 34;
		t.x = 97;
		t.y = 365;
		t.skinName = TheFarmLobbySkin$Skin26;
		return t;
	};
	_proto.manure2_i = function () {
		var t = new eui.Button();
		this.manure2 = t;
		t.height = 30;
		t.label = "";
		t.visible = false;
		t.width = 34;
		t.x = 327;
		t.y = 339;
		t.skinName = TheFarmLobbySkin$Skin27;
		return t;
	};
	_proto.manure3_i = function () {
		var t = new eui.Button();
		this.manure3 = t;
		t.height = 30;
		t.label = "";
		t.visible = false;
		t.width = 34;
		t.x = 192;
		t.y = 467;
		t.skinName = TheFarmLobbySkin$Skin28;
		return t;
	};
	_proto.manure1_bj_i = function () {
		var t = new eui.Image();
		this.manure1_bj = t;
		t.height = 12;
		t.source = "juxingluse@3x_png";
		t.visible = false;
		t.width = 40;
		t.x = 95;
		t.y = 405;
		return t;
	};
	_proto.manure2_bj_i = function () {
		var t = new eui.Image();
		this.manure2_bj = t;
		t.height = 12;
		t.source = "juxingluse@3x_png";
		t.visible = false;
		t.width = 40;
		t.x = 199;
		t.y = 500;
		return t;
	};
	_proto.manure3_bj_i = function () {
		var t = new eui.Image();
		this.manure3_bj = t;
		t.height = 12;
		t.source = "juxingluse@3x_png";
		t.visible = false;
		t.width = 40;
		t.x = 323;
		t.y = 370;
		return t;
	};
	_proto.manure1_text_i = function () {
		var t = new eui.Label();
		this.manure1_text = t;
		t.height = 12;
		t.size = 9;
		t.text = "0";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 40;
		t.x = 96;
		t.y = 405;
		return t;
	};
	_proto.manure2_text_i = function () {
		var t = new eui.Label();
		this.manure2_text = t;
		t.height = 12;
		t.size = 9;
		t.text = "0";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 40;
		t.x = 199;
		t.y = 500;
		return t;
	};
	_proto.manure3_text_i = function () {
		var t = new eui.Label();
		this.manure3_text = t;
		t.height = 12;
		t.size = 9;
		t.text = "0";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 40;
		t.x = 324;
		t.y = 370;
		return t;
	};
	_proto.AggregateScheduling1_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling1 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 184;
		t.y = 404;
		return t;
	};
	_proto.Scheduling1_i = function () {
		var t = new eui.Image();
		this.Scheduling1 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 183;
		t.y = 405;
		return t;
	};
	_proto.Scheduling_Number1_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number1 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 313.88;
		t.y = 333.04;
		return t;
	};
	_proto.AggregateScheduling9_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling9 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 310;
		t.y = 310;
		return t;
	};
	_proto.Scheduling9_i = function () {
		var t = new eui.Image();
		this.Scheduling9 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 310;
		t.y = 310;
		return t;
	};
	_proto.Scheduling_Number9_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number9 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 323.88;
		t.y = 343.04;
		return t;
	};
	_proto.AggregateScheduling2_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling2 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 320;
		t.y = 320;
		return t;
	};
	_proto.Scheduling2_i = function () {
		var t = new eui.Image();
		this.Scheduling2 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 320;
		t.y = 320;
		return t;
	};
	_proto.Scheduling_Number2_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number2 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 333.88;
		t.y = 353.04;
		return t;
	};
	_proto.AggregateScheduling3_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling3 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 330;
		t.y = 330;
		return t;
	};
	_proto.Scheduling3_i = function () {
		var t = new eui.Image();
		this.Scheduling3 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 330;
		t.y = 330;
		return t;
	};
	_proto.Scheduling_Number3_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number3 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 343.88;
		t.y = 363.04;
		return t;
	};
	_proto.AggregateScheduling4_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling4 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 340;
		t.y = 340;
		return t;
	};
	_proto.Scheduling4_i = function () {
		var t = new eui.Image();
		this.Scheduling4 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 340;
		t.y = 340;
		return t;
	};
	_proto.Scheduling_Number4_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number4 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 353.88;
		t.y = 373.04;
		return t;
	};
	_proto.AggregateScheduling5_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling5 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 350;
		t.y = 350;
		return t;
	};
	_proto.Scheduling5_i = function () {
		var t = new eui.Image();
		this.Scheduling5 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 350;
		t.y = 350;
		return t;
	};
	_proto.Scheduling_Number5_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number5 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 363.88;
		t.y = 383.04;
		return t;
	};
	_proto.AggregateScheduling6_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling6 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 360;
		t.y = 360;
		return t;
	};
	_proto.Scheduling6_i = function () {
		var t = new eui.Image();
		this.Scheduling6 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 360;
		t.y = 360;
		return t;
	};
	_proto.Scheduling_Number6_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number6 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 373.88;
		t.y = 393.04;
		return t;
	};
	_proto.AggregateScheduling7_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling7 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 370;
		t.y = 370;
		return t;
	};
	_proto.Scheduling7_i = function () {
		var t = new eui.Image();
		this.Scheduling7 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 370;
		t.y = 370;
		return t;
	};
	_proto.Scheduling_Number7_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number7 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 383.88;
		t.y = 403.04;
		return t;
	};
	_proto.AggregateScheduling8_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling8 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 380;
		t.y = 380;
		return t;
	};
	_proto.Scheduling8_i = function () {
		var t = new eui.Image();
		this.Scheduling8 = t;
		t.height = 64;
		t.source = "chanliang@3x_png";
		t.visible = false;
		t.width = 71;
		t.x = 380;
		t.y = 380;
		return t;
	};
	_proto.Scheduling_Number8_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number8 = t;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 60;
		t.x = 393;
		t.y = 415;
		return t;
	};
	_proto.SJ9_i = function () {
		var t = new eui.Image();
		this.SJ9 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 88;
		t.y = 316;
		return t;
	};
	_proto.SJ1_i = function () {
		var t = new eui.Image();
		this.SJ1 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 98;
		t.y = 326;
		return t;
	};
	_proto.SJ2_i = function () {
		var t = new eui.Image();
		this.SJ2 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 108;
		t.y = 336;
		return t;
	};
	_proto.SJ3_i = function () {
		var t = new eui.Image();
		this.SJ3 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 118;
		t.y = 346;
		return t;
	};
	_proto.SJ4_i = function () {
		var t = new eui.Image();
		this.SJ4 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 128;
		t.y = 356;
		return t;
	};
	_proto.SJ5_i = function () {
		var t = new eui.Image();
		this.SJ5 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 138;
		t.y = 366;
		return t;
	};
	_proto.SJ6_i = function () {
		var t = new eui.Image();
		this.SJ6 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 148;
		t.y = 376;
		return t;
	};
	_proto.SJ7_i = function () {
		var t = new eui.Image();
		this.SJ7 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 158;
		t.y = 386;
		return t;
	};
	_proto.SJ8_i = function () {
		var t = new eui.Image();
		this.SJ8 = t;
		t.height = 13;
		t.source = "shijian@2x_png";
		t.visible = false;
		t.width = 13;
		t.x = 168;
		t.y = 396;
		return t;
	};
	_proto.Options_i = function () {
		var t = new eui.Group();
		this.Options = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image19_i(),this._Image20_i(),this.Music_i(),this._Label1_i(),this._Label2_i(),this.SoundEffect_i(),this._Image21_i(),this.Options_close_i()];
		return t;
	};
	_proto._Image19_i = function () {
		var t = new eui.Image();
		t.height = 900;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image20_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 251;
		t.source = "set2_png";
		t.width = 328;
		t.x = 24;
		t.y = 215;
		return t;
	};
	_proto.Music_i = function () {
		var t = new eui.HSlider();
		this.Music = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 36;
		t.maximum = 100;
		t.value = 100;
		t.width = 175;
		t.x = 119;
		t.y = 287;
		return t;
	};
	_proto._Label1_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.size = 18;
		t.text = "音乐";
		t.textAlign = "center";
		t.textColor = 0x2abbaa;
		t.verticalAlign = "middle";
		t.width = 36;
		t.x = 77;
		t.y = 297;
		return t;
	};
	_proto._Label2_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17;
		t.size = 18;
		t.text = "音效";
		t.textAlign = "center";
		t.textColor = 0x2abbaa;
		t.verticalAlign = "middle";
		t.width = 36;
		t.x = 77;
		t.y = 353;
		return t;
	};
	_proto.SoundEffect_i = function () {
		var t = new eui.HSlider();
		this.SoundEffect = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 36;
		t.width = 175;
		t.x = 119;
		t.y = 343.8;
		return t;
	};
	_proto._Image21_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.source = "shezhi(1)_png";
		t.width = 147;
		t.x = 116;
		t.y = 208;
		return t;
	};
	_proto.Options_close_i = function () {
		var t = new eui.Button();
		this.Options_close = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 300;
		t.y = 219;
		t.skinName = TheFarmLobbySkin$Skin29;
		return t;
	};
	_proto.MyCenter_i = function () {
		var t = new eui.Group();
		this.MyCenter = t;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image22_i(),this._Image23_i(),this._Image24_i(),this.MyCenter_close_i(),this._Image25_i(),this.ChangeName_i(),this.modification_i(),this._Image26_i(),this._Image27_i(),this.CurrentTitleLevel_i(),this.detail_i(),this.TheCurrentMoney_i(),this.TotalMoney_i(),this.TheNextLevel_i(),this.TotalMoneyTow_i(),this.TheNextLevelMoney_i(),this._Label3_i(),this._Label4_i(),this._Label5_i(),this._Label6_i(),this.fertilizer_i(),this._Button2_i(),this.plan_i(),this.schedule_i(),this._Image28_i(),this._Image29_i(),this._Image30_i(),this._Image31_i(),this._Image32_i(),this._Image33_i(),this.Money_center_i(),this._Image34_i(),this._Image35_i()];
		return t;
	};
	_proto._Image22_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image23_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 570;
		t.source = "beijing123@3x_png";
		t.width = 353;
		t.x = 15;
		t.y = 47;
		return t;
	};
	_proto._Image24_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "GRZIZDBJHS@3x_png";
		t.width = 300;
		t.x = 38;
		t.y = 135;
		return t;
	};
	_proto.MyCenter_close_i = function () {
		var t = new eui.Button();
		this.MyCenter_close = t;
		t.height = 34;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 54;
		t.skinName = TheFarmLobbySkin$Skin30;
		return t;
	};
	_proto._Image25_i = function () {
		var t = new eui.Image();
		t.height = 29;
		t.source = "juxingH@3x_png";
		t.width = 194;
		t.x = 90;
		t.y = 140;
		return t;
	};
	_proto.ChangeName_i = function () {
		var t = new eui.TextInput();
		this.ChangeName = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 29;
		t.maxChars = 3;
		t.textColor = 0xab803c;
		t.width = 194;
		t.x = 90;
		t.y = 140;
		return t;
	};
	_proto.modification_i = function () {
		var t = new eui.Button();
		this.modification = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.width = 38;
		t.x = 288;
		t.y = 136;
		t.skinName = TheFarmLobbySkin$Skin31;
		return t;
	};
	_proto._Image26_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 135;
		t.source = "GRZIZDBJHS@3x_png";
		t.width = 300;
		t.x = 38;
		t.y = 394;
		return t;
	};
	_proto._Image27_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 165;
		t.source = "GRZIZDBJHS@3x_png";
		t.width = 300;
		t.x = 38;
		t.y = 185;
		return t;
	};
	_proto.CurrentTitleLevel_i = function () {
		var t = new eui.Image();
		this.CurrentTitleLevel = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "dizhu@3x_png";
		t.width = 60;
		t.x = 149;
		t.y = 196;
		return t;
	};
	_proto.detail_i = function () {
		var t = new eui.Button();
		this.detail = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 33;
		t.label = "";
		t.width = 38;
		t.x = 288;
		t.y = 196;
		t.skinName = TheFarmLobbySkin$Skin32;
		return t;
	};
	_proto.TheCurrentMoney_i = function () {
		var t = new eui.Label();
		this.TheCurrentMoney = t;
		t.fontFamily = "SimSun";
		t.height = 15;
		t.size = 16;
		t.text = "Label";
		t.textColor = 0x2abbaa;
		t.verticalAlign = "middle";
		t.x = 135;
		t.y = 225;
		return t;
	};
	_proto.TotalMoney_i = function () {
		var t = new eui.Label();
		this.TotalMoney = t;
		t.fontFamily = "SimSun";
		t.height = 16;
		t.size = 16;
		t.text = "0";
		t.textColor = 0x2abbaa;
		t.verticalAlign = "middle";
		t.x = 135;
		t.y = 250;
		return t;
	};
	_proto.TheNextLevel_i = function () {
		var t = new eui.Label();
		this.TheNextLevel = t;
		t.height = 15;
		t.size = 13;
		t.text = "中农";
		t.textColor = 0x99b610;
		t.verticalAlign = "middle";
		t.x = 52;
		t.y = 276;
		return t;
	};
	_proto.TotalMoneyTow_i = function () {
		var t = new eui.Label();
		this.TotalMoneyTow = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 12;
		t.size = 13;
		t.text = "0g";
		t.textAlign = "center";
		t.textColor = 0x2abbaa;
		t.verticalAlign = "justify";
		t.x = 52;
		t.y = 319;
		return t;
	};
	_proto.TheNextLevelMoney_i = function () {
		var t = new eui.Label();
		this.TheNextLevelMoney = t;
		t.height = 12;
		t.size = 13;
		t.text = "99g";
		t.textAlign = "center";
		t.textColor = 0x2abbaa;
		t.verticalAlign = "middle";
		t.x = 292;
		t.y = 319;
		return t;
	};
	_proto._Label3_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 12;
		t.size = 12;
		t.text = "1.点击道具购买肥料加大产量。";
		t.textColor = 0x2abbaa;
		t.x = 52;
		t.y = 405;
		return t;
	};
	_proto._Label4_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 12;
		t.size = 12;
		t.text = "2.进好友庄园偷取好友的金钱。";
		t.textColor = 0x2abbaa;
		t.x = 52;
		t.y = 427;
		return t;
	};
	_proto._Label5_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 12;
		t.size = 12;
		t.text = "3.评价商城的购物订单。";
		t.textColor = 0x2abbaa;
		t.x = 52;
		t.y = 449;
		return t;
	};
	_proto._Label6_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 116;
		t.size = 24;
		t.text = "4.发红包让别人帮忙浇水。";
		t.textColor = 0x000000;
		t.visible = false;
		t.width = 334;
		t.x = 42;
		t.y = 838;
		return t;
	};
	_proto.fertilizer_i = function () {
		var t = new eui.Button();
		this.fertilizer = t;
		t.height = 24;
		t.label = "";
		t.width = 65;
		t.x = 260;
		t.y = 402;
		t.skinName = TheFarmLobbySkin$Skin33;
		return t;
	};
	_proto._Button2_i = function () {
		var t = new eui.Button();
		t.label = "";
		t.visible = false;
		t.x = 376;
		t.y = 838;
		t.skinName = TheFarmLobbySkin$Skin34;
		return t;
	};
	_proto.plan_i = function () {
		var t = new eui.Image();
		this.plan = t;
		t.height = 9;
		t.source = "juxing(2)_png";
		t.width = 270;
		t.x = 53;
		t.y = 300;
		return t;
	};
	_proto.schedule_i = function () {
		var t = new eui.Image();
		this.schedule = t;
		t.height = 9;
		t.source = "jixing8_png";
		t.width = 0;
		t.x = 50;
		t.y = 300;
		return t;
	};
	_proto._Image28_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 15;
		t.source = "leijihulubi@3x_png";
		t.width = 76;
		t.x = 51;
		t.y = 249;
		return t;
	};
	_proto._Image29_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 15;
		t.source = "dengji@3x_png";
		t.width = 90;
		t.x = 51;
		t.y = 198;
		return t;
	};
	_proto._Image30_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 15;
		t.source = "dangqianhulubi@3x_png";
		t.width = 76;
		t.x = 51;
		t.y = 224;
		return t;
	};
	_proto._Image31_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "name@3x_png";
		t.width = 33;
		t.x = 51;
		t.y = 146;
		return t;
	};
	_proto._Image32_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 53;
		t.source = "beijing@3x(1)_png";
		t.width = 141;
		t.x = 115;
		t.y = 67;
		return t;
	};
	_proto._Image33_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 18;
		t.x = 154;
		t.y = 90;
		return t;
	};
	_proto.Money_center_i = function () {
		var t = new eui.Label();
		this.Money_center = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 13;
		t.size = 18;
		t.text = "0";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.x = 180;
		t.y = 97;
		return t;
	};
	_proto._Image34_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "ziliao@3x_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto._Image35_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "fangfa@3x_png";
		t.width = 163;
		t.x = 51;
		t.y = 361;
		return t;
	};
	_proto.Designation_i = function () {
		var t = new eui.Group();
		this.Designation = t;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image36_i(),this._Image37_i(),this.Designation_close_i(),this._Image38_i(),this._Image39_i(),this._Image40_i(),this._Image41_i(),this._Image42_i(),this._Image43_i(),this._Image44_i(),this._Image45_i(),this._Image46_i(),this._Label7_i(),this._Label8_i(),this._Label9_i(),this._Label10_i(),this._Label11_i(),this._Label12_i(),this._Label13_i(),this._Label14_i(),this._Label15_i(),this._Image47_i(),this._Image48_i(),this._Image49_i(),this.CHQD_i()];
		return t;
	};
	_proto._Image36_i = function () {
		var t = new eui.Image();
		t.anchorOffsetY = 0;
		t.height = 900;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image37_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 420;
		t.source = "bj_png";
		t.width = 328;
		t.x = 22.67;
		t.y = 126;
		return t;
	};
	_proto.Designation_close_i = function () {
		var t = new eui.Button();
		this.Designation_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 314;
		t.y = 140;
		t.skinName = TheFarmLobbySkin$Skin35;
		return t;
	};
	_proto._Image38_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "dianhu@3x_png";
		t.width = 66;
		t.x = 65.32;
		t.y = 211;
		return t;
	};
	_proto._Image39_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "pinmin@3x_png";
		t.width = 66;
		t.x = 65.32;
		t.y = 237;
		return t;
	};
	_proto._Image40_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "zhongnong@3x_png";
		t.width = 66;
		t.x = 65.32;
		t.y = 263;
		return t;
	};
	_proto._Image41_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "funong@3x_png";
		t.width = 66;
		t.x = 65.32;
		t.y = 288;
		return t;
	};
	_proto._Image42_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "dizhu@3x_png";
		t.width = 66;
		t.x = 65.32;
		t.y = 314;
		return t;
	};
	_proto._Image43_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "fuwong@3x_png";
		t.width = 66;
		t.x = 66.65;
		t.y = 340;
		return t;
	};
	_proto._Image44_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "zhuangzhu@3x_png";
		t.width = 66;
		t.x = 66.65;
		t.y = 366;
		return t;
	};
	_proto._Image45_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "lingzhu@3x_png";
		t.width = 66;
		t.x = 66.65;
		t.y = 391;
		return t;
	};
	_proto._Image46_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "qiuzhang@3x_png";
		t.width = 66;
		t.x = 66.65;
		t.y = 417;
		return t;
	};
	_proto._Label7_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 14;
		t.size = 14;
		t.text = "0-40";
		t.textColor = 0x208d99;
		t.x = 220;
		t.y = 213;
		return t;
	};
	_proto._Label8_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "30000-99999";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 190;
		t.y = 367;
		return t;
	};
	_proto._Label9_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "100000-299999";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 181;
		t.y = 393;
		return t;
	};
	_proto._Label10_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "300000以上";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 198;
		t.y = 418;
		return t;
	};
	_proto._Label11_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "40-99";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 216;
		t.y = 238;
		return t;
	};
	_proto._Label12_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "100-299";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 209;
		t.y = 264;
		return t;
	};
	_proto._Label13_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "300-999";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 209;
		t.y = 289;
		return t;
	};
	_proto._Label14_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "1000-4999";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 200;
		t.y = 316;
		return t;
	};
	_proto._Label15_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 16;
		t.size = 15;
		t.text = "5000-29999";
		t.textAlign = "left";
		t.textColor = 0x208d99;
		t.verticalAlign = "middle";
		t.x = 195;
		t.y = 341;
		return t;
	};
	_proto._Image47_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "chenghao_png";
		t.width = 146;
		t.x = 119;
		t.y = 115;
		return t;
	};
	_proto._Image48_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "title@3x_png";
		t.width = 33;
		t.x = 101.65;
		t.y = 181;
		return t;
	};
	_proto._Image49_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.source = "LJHLB@3x_png";
		t.width = 77;
		t.x = 205;
		t.y = 182;
		return t;
	};
	_proto.CHQD_i = function () {
		var t = new eui.Button();
		this.CHQD = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 32;
		t.label = "";
		t.width = 75;
		t.x = 151;
		t.y = 458;
		t.skinName = TheFarmLobbySkin$Skin36;
		return t;
	};
	_proto.TheIndianArrowheads_i = function () {
		var t = new eui.Group();
		this.TheIndianArrowheads = t;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image50_i(),this._Image51_i(),this._Image52_i(),this.TheIndianArrowheads_close_i(),this._Image53_i(),this.Goldhoe_i(),this._Image54_i(),this._Image55_i(),this.IndianArrowheadsNumber_i(),this._Image56_i(),this.Indian_arrowheads_record1_i(),this._Image57_i(),this._Image58_i(),this._Image59_i(),this.platinumhoe_i(),this.diamondhoe_i(),this.platinumhoe_zhi_i(),this.diamondhoe_zhi_i(),this.gundong1_i(),this.wenzi1_i(),this.inform_i(),this.Indian_arrowheads_record2_i(),this.WBCS_i(),this.WBXX_i()];
		return t;
	};
	_proto._Image50_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image51_i = function () {
		var t = new eui.Image();
		t.anchorOffsetY = 0;
		t.height = 525.61;
		t.source = "beijing123@3x_png";
		t.width = 346;
		t.x = 19;
		t.y = 43;
		return t;
	};
	_proto._Image52_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 57;
		t.source = "fenge2_png";
		t.width = 300;
		t.x = 37;
		t.y = 93;
		return t;
	};
	_proto.TheIndianArrowheads_close_i = function () {
		var t = new eui.Button();
		this.TheIndianArrowheads_close = t;
		t.height = 34;
		t.label = "";
		t.width = 34;
		t.x = 312;
		t.y = 51;
		t.skinName = TheFarmLobbySkin$Skin37;
		return t;
	};
	_proto._Image53_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "wabao2_png";
		t.width = 168;
		t.x = 105;
		t.y = 35;
		return t;
	};
	_proto.Goldhoe_i = function () {
		var t = new eui.Button();
		this.Goldhoe = t;
		t.anchorOffsetX = 0;
		t.height = 112;
		t.label = "";
		t.width = 100;
		t.x = 35;
		t.y = 208;
		t.skinName = TheFarmLobbySkin$Skin38;
		return t;
	};
	_proto._Image54_i = function () {
		var t = new eui.Image();
		t.height = 34;
		t.source = "juxingQMWBCSBJ@3x_png";
		t.width = 61;
		t.x = 129;
		t.y = 493.26;
		return t;
	};
	_proto._Image55_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 134.36;
		t.source = "baoxiang@2x_png";
		t.width = 189;
		t.x = 88;
		t.y = 326;
		return t;
	};
	_proto.IndianArrowheadsNumber_i = function () {
		var t = new eui.Label();
		this.IndianArrowheadsNumber = t;
		t.height = 15;
		t.size = 16;
		t.text = "1次";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.x = 148;
		t.y = 502.78;
		return t;
	};
	_proto._Image56_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 2;
		t.source = "fenge@2x_png";
		t.width = 316;
		t.x = 29.2;
		t.y = 473.2;
		return t;
	};
	_proto.Indian_arrowheads_record1_i = function () {
		var t = new eui.Button();
		this.Indian_arrowheads_record1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.label = "";
		t.width = 83;
		t.x = 216;
		t.y = 92;
		t.skinName = TheFarmLobbySkin$Skin39;
		return t;
	};
	_proto._Image57_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 21;
		t.source = "wenzi@3x_png";
		t.width = 95;
		t.x = 35;
		t.y = 500.28;
		return t;
	};
	_proto._Image58_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "kaishi@3x_png";
		t.width = 209;
		t.x = 84;
		t.y = 171;
		return t;
	};
	_proto._Image59_i = function () {
		var t = new eui.Image();
		t.height = 42;
		t.source = "wenziDYR@3x_png";
		t.width = 133;
		t.x = 199;
		t.y = 491.27;
		return t;
	};
	_proto.platinumhoe_i = function () {
		var t = new eui.Image();
		this.platinumhoe = t;
		t.height = 112;
		t.source = "baijinchutouQMWB@3x_png";
		t.touchEnabled = true;
		t.width = 100;
		t.x = 138;
		t.y = 208;
		return t;
	};
	_proto.diamondhoe_i = function () {
		var t = new eui.Image();
		this.diamondhoe = t;
		t.height = 112;
		t.source = "zuanshichutouQMWB@3x_png";
		t.touchEnabled = true;
		t.width = 100;
		t.x = 240;
		t.y = 207;
		return t;
	};
	_proto.platinumhoe_zhi_i = function () {
		var t = new eui.Label();
		this.platinumhoe_zhi = t;
		t.size = 12;
		t.text = "";
		t.x = 210;
		t.y = 275;
		return t;
	};
	_proto.diamondhoe_zhi_i = function () {
		var t = new eui.Label();
		this.diamondhoe_zhi = t;
		t.size = 12;
		t.text = "";
		t.x = 310;
		t.y = 275;
		return t;
	};
	_proto.gundong1_i = function () {
		var t = new eui.Image();
		this.gundong1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "red_png";
		t.width = 213;
		t.x = 79;
		t.y = 124;
		return t;
	};
	_proto.wenzi1_i = function () {
		var t = new eui.Label();
		this.wenzi1 = t;
		t.size = 12;
		t.text = "恭喜小猪佩奇成功购买了2倍肥料";
		t.x = 94;
		t.y = 150;
		return t;
	};
	_proto.inform_i = function () {
		var t = new eui.Group();
		this.inform = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 215;
		t.visible = false;
		t.width = 300;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image60_i(),this._Image61_i(),this.inform_frame_i(),this.inform_close_i(),this._Button3_i(),this._Image62_i()];
		return t;
	};
	_proto._Image60_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image61_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 219;
		t.source = "beijingBKD@3x_png";
		t.width = 304;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto.inform_frame_i = function () {
		var t = new eui.Label();
		this.inform_frame = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 38;
		t.size = 15;
		t.text = "对不起您还没有购买白金锄头，请到道具商店购买后使用。";
		t.textColor = 0x146776;
		t.width = 238;
		t.x = 66;
		t.y = 295;
		return t;
	};
	_proto.inform_close_i = function () {
		var t = new eui.Button();
		this.inform_close = t;
		t.height = 36;
		t.label = "";
		t.width = 76;
		t.x = 146;
		t.y = 356;
		t.skinName = TheFarmLobbySkin$Skin40;
		return t;
	};
	_proto._Button3_i = function () {
		var t = new eui.Button();
		t.height = 34;
		t.label = "";
		t.width = 34;
		t.x = 309;
		t.y = 207;
		t.skinName = TheFarmLobbySkin$Skin41;
		return t;
	};
	_proto._Image62_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.scaleY = 0.9;
		t.source = "tishi@3x_png";
		t.width = 204;
		t.x = 84;
		t.y = 201.33;
		return t;
	};
	_proto.Indian_arrowheads_record2_i = function () {
		var t = new eui.Group();
		this.Indian_arrowheads_record2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image63_i(),this._Image64_i(),this._Image65_i(),this._Scroller1_i(),this.Indian_arrowheads_record2_close_i()];
		return t;
	};
	_proto._Image63_i = function () {
		var t = new eui.Image();
		t.anchorOffsetY = 0;
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image64_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 505;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijingBKG@3x_png";
		t.width = 330;
		t.x = 23;
		t.y = 73;
		return t;
	};
	_proto._Image65_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "jilu@2x_png";
		t.width = 214;
		t.x = 84;
		t.y = 57;
		return t;
	};
	_proto._Scroller1_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 375;
		t.width = 276;
		t.x = 50;
		t.y = 136;
		t.viewport = this.Log_message_i();
		return t;
	};
	_proto.Log_message_i = function () {
		var t = new eui.Group();
		this.Log_message = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 268;
		t.width = 419;
		return t;
	};
	_proto.Indian_arrowheads_record2_close_i = function () {
		var t = new eui.Button();
		this.Indian_arrowheads_record2_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 325;
		t.y = 66;
		t.skinName = TheFarmLobbySkin$Skin42;
		return t;
	};
	_proto.WBCS_i = function () {
		var t = new eui.Group();
		this.WBCS = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image66_i(),this._Image67_i(),this._Image68_i(),this._Label16_i(),this.WBCS__close_i()];
		return t;
	};
	_proto._Image66_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image67_i = function () {
		var t = new eui.Image();
		t.height = 219;
		t.source = "beijingBKD@3x_png";
		t.width = 304;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto._Image68_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.scaleY = 0.9;
		t.source = "tishi@3x_png";
		t.width = 204;
		t.x = 84;
		t.y = 201.33;
		return t;
	};
	_proto._Label16_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 112;
		t.size = 20;
		t.text = "今日挖宝次数已用尽";
		t.textColor = 0x000000;
		t.width = 251;
		t.x = 62;
		t.y = 276.74;
		return t;
	};
	_proto.WBCS__close_i = function () {
		var t = new eui.Button();
		this.WBCS__close = t;
		t.height = 34;
		t.label = "";
		t.width = 34;
		t.x = 309;
		t.y = 207;
		t.skinName = TheFarmLobbySkin$Skin43;
		return t;
	};
	_proto.WBXX_i = function () {
		var t = new eui.Group();
		this.WBXX = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image69_i(),this._Image70_i(),this._Image71_i(),this.WBXX_zi_i(),this.WBXX__close_i()];
		return t;
	};
	_proto._Image69_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image70_i = function () {
		var t = new eui.Image();
		t.height = 219;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijingBKD@3x_png";
		t.width = 304;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto._Image71_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.scaleX = 1;
		t.scaleY = 0.9;
		t.source = "tishi@3x_png";
		t.width = 204;
		t.x = 84;
		t.y = 201.33;
		return t;
	};
	_proto.WBXX_zi_i = function () {
		var t = new eui.Label();
		this.WBXX_zi = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 112;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 20;
		t.text = "今日挖宝次数已用尽";
		t.textColor = 0x000000;
		t.width = 251;
		t.x = 62;
		t.y = 276.74;
		return t;
	};
	_proto.WBXX__close_i = function () {
		var t = new eui.Button();
		this.WBXX__close = t;
		t.height = 34;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 34;
		t.x = 309;
		t.y = 207;
		t.skinName = TheFarmLobbySkin$Skin44;
		return t;
	};
	_proto.ForRecord_i = function () {
		var t = new eui.Group();
		this.ForRecord = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image72_i(),this._Image73_i(),this._Image74_i(),this._Image75_i(),this._Image76_i(),this.ForRecord_close_i(),this.money_i(),this._Image77_i(),this.RecordStrip_i(),this._Image78_i(),this._Image79_i(),this._Label17_i(),this.GoAndSee_i()];
		return t;
	};
	_proto._Image72_i = function () {
		var t = new eui.Image();
		t.anchorOffsetY = 0;
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image73_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 529.09;
		t.source = "beijing123@3x_png";
		t.width = 353;
		t.x = 15;
		t.y = 45.76;
		return t;
	};
	_proto._Image74_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75;
		t.source = "fenge@3x_png";
		t.width = 329;
		t.x = 24;
		t.y = 86;
		return t;
	};
	_proto._Image75_i = function () {
		var t = new eui.Image();
		t.height = 22;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 18;
		t.x = 81;
		t.y = 106;
		return t;
	};
	_proto._Image76_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "jux@2x(2)_png";
		t.width = 72;
		t.x = 110;
		t.y = 109;
		return t;
	};
	_proto.ForRecord_close_i = function () {
		var t = new eui.Button();
		this.ForRecord_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 325;
		t.y = 66;
		t.skinName = TheFarmLobbySkin$Skin45;
		return t;
	};
	_proto.money_i = function () {
		var t = new eui.Label();
		this.money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.size = 13;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0xffffff;
		t.verticalAlign = "middle";
		t.width = 67;
		t.x = 114;
		t.y = 109;
		return t;
	};
	_proto._Image77_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 35.3;
		t.source = "juxingQMWBCSBJ@3x_png";
		t.width = 103.23;
		t.x = 48;
		t.y = 507.77;
		return t;
	};
	_proto.RecordStrip_i = function () {
		var t = new eui.Scroller();
		this.RecordStrip = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 310;
		t.width = 275;
		t.x = 50;
		t.y = 163.92;
		t.viewport = this.RecordBox_i();
		return t;
	};
	_proto.RecordBox_i = function () {
		var t = new eui.Group();
		this.RecordBox = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 306.67;
		t.width = 390;
		t.y = 43.94;
		return t;
	};
	_proto._Image78_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "shouqujilu@3x_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto._Image79_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 21;
		t.source = "HLBKYY_png";
		t.width = 94;
		t.x = 49;
		t.y = 482.32;
		return t;
	};
	_proto._Label17_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 17.5;
		t.size = 16;
		t.text = "兑换商品";
		t.width = 70;
		t.x = 68;
		t.y = 518;
		return t;
	};
	_proto.GoAndSee_i = function () {
		var t = new eui.Label();
		this.GoAndSee = t;
		t.height = 12;
		t.size = 12;
		t.text = "获取更多的葫芦币 >>";
		t.textColor = 0x2abbaa;
		t.x = 197;
		t.y = 114;
		return t;
	};
	_proto.BrowserHelpMenu_i = function () {
		var t = new eui.Group();
		this.BrowserHelpMenu = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image80_i(),this._Image81_i(),this.BrowserHelpMenu_close_i(),this._Image82_i(),this._Image83_i(),this.RaiseMoney_i(),this.Water_i(),this.Seed_i(),this.PetPig_i(),this._Scroller2_i()];
		return t;
	};
	_proto._Image80_i = function () {
		var t = new eui.Image();
		t.height = 900;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image81_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 415;
		t.source = "beijing123@3x_png";
		t.width = 352;
		t.x = 15;
		t.y = 90;
		return t;
	};
	_proto.BrowserHelpMenu_close_i = function () {
		var t = new eui.Button();
		this.BrowserHelpMenu_close = t;
		t.anchorOffsetX = 0;
		t.height = 34;
		t.label = "";
		t.width = 34;
		t.x = 312;
		t.y = 99;
		t.skinName = TheFarmLobbySkin$Skin46;
		return t;
	};
	_proto._Image82_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 260;
		t.source = "JXL5@3x_png";
		t.width = 299;
		t.x = 38;
		t.y = 204;
		return t;
	};
	_proto._Image83_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "banghzu_png";
		t.width = 168;
		t.x = 105;
		t.y = 83;
		return t;
	};
	_proto.RaiseMoney_i = function () {
		var t = new eui.Image();
		this.RaiseMoney = t;
		t.anchorOffsetX = 0;
		t.height = 36;
		t.source = "hulu@3x0_png";
		t.width = 60;
		t.x = 42.83;
		t.y = 151;
		return t;
	};
	_proto.Water_i = function () {
		var t = new eui.Image();
		this.Water = t;
		t.anchorOffsetX = 0;
		t.height = 36;
		t.source = "jiaoshui@3x0_png";
		t.width = 60;
		t.x = 120.83;
		t.y = 151;
		return t;
	};
	_proto.Seed_i = function () {
		var t = new eui.Image();
		this.Seed = t;
		t.anchorOffsetX = 0;
		t.height = 36;
		t.source = "zhongzi@3x0_png";
		t.width = 60;
		t.x = 196.5;
		t.y = 151;
		return t;
	};
	_proto.PetPig_i = function () {
		var t = new eui.Image();
		this.PetPig = t;
		t.anchorOffsetX = 0;
		t.height = 36;
		t.source = "chongwu@3x0_png";
		t.width = 60;
		t.x = 274.17;
		t.y = 151;
		return t;
	};
	_proto._Scroller2_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 265;
		t.width = 295;
		t.x = 40;
		t.y = 200;
		t.viewport = this._Group1_i();
		return t;
	};
	_proto._Group1_i = function () {
		var t = new eui.Group();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 284.36;
		t.width = 295.67;
		t.elementsContent = [this.HelpText_i(),this.HelpText0_i(),this.HelpText1_i(),this.HelpText2_i()];
		return t;
	};
	_proto.HelpText_i = function () {
		var t = new eui.Label();
		this.HelpText = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.backgroundColor = 0x000000;
		t.borderColor = 0x000000;
		t.height = 245;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.strokeColor = 0x000000;
		t.text = "1. 无肥料耕种的情况下，葫芦没24小时产8g葫芦币，购买肥料可使葫芦币的产量翻倍。\n2. 当葫芦币达到一定数量时，可前往兑换页面兑换商品。\n3. 可以去好友家偷取一定数量的葫芦币。";
		t.textColor = 0x285970;
		t.visible = false;
		t.width = 281;
		t.x = 7;
		t.y = 10;
		return t;
	};
	_proto.HelpText0_i = function () {
		var t = new eui.Label();
		this.HelpText0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 245;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "1. 发现自家土地干旱时，即可浇水。\n2. 可前往好友家为好友浇水。";
		t.textColor = 0x285970;
		t.visible = false;
		t.width = 281;
		t.x = 7;
		t.y = 10;
		return t;
	};
	_proto.HelpText1_i = function () {
		var t = new eui.Label();
		this.HelpText1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 245;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "1. 种子是庄园的特殊道具，种子成熟可收获些葫芦币，花费一定的葫芦币来兑换商品。\n2. 在耕种期间，用户需在庄园内进行日常打理来帮助作物生长。";
		t.textColor = 0x285970;
		t.visible = false;
		t.width = 281;
		t.x = 7;
		t.y = 10;
		return t;
	};
	_proto.HelpText2_i = function () {
		var t = new eui.Label();
		this.HelpText2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 245;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "1.宠物在拥有狗粮后，可守护自家作物防止好友偷取。";
		t.textColor = 0x285970;
		t.visible = false;
		t.width = 281;
		t.x = 7;
		t.y = 10;
		return t;
	};
	_proto.commodity_i = function () {
		var t = new eui.Group();
		this.commodity = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image84_i(),this._Image85_i(),this.PropsFor_close_i(),this._Image86_i(),this._Image87_i(),this.ContentProps_i(),this.Conversion_i(),this.PropsClass_i(),this.ConversionGoods_i()];
		return t;
	};
	_proto._Image84_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image85_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 548.79;
		t.source = "beijing123@3x_png";
		t.width = 353;
		t.x = 15;
		t.y = 47;
		return t;
	};
	_proto.PropsFor_close_i = function () {
		var t = new eui.Button();
		this.PropsFor_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 312;
		t.y = 56;
		t.skinName = TheFarmLobbySkin$Skin47;
		return t;
	};
	_proto._Image86_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "daojuduihuan_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto._Image87_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "fenge2_png";
		t.width = 300;
		t.x = 38;
		t.y = 143;
		return t;
	};
	_proto.ContentProps_i = function () {
		var t = new eui.Image();
		this.ContentProps = t;
		t.height = 45;
		t.source = "daoju@3x_png";
		t.width = 130;
		t.x = 62;
		t.y = 84;
		return t;
	};
	_proto.Conversion_i = function () {
		var t = new eui.Image();
		this.Conversion = t;
		t.height = 45;
		t.source = "duihuan@3x_png";
		t.width = 130;
		t.x = 184;
		t.y = 84;
		return t;
	};
	_proto.PropsClass_i = function () {
		var t = new eui.Group();
		this.PropsClass = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.warehouse_i(),this._Image88_i(),this.prop_money_i(),this.PropsRecord_i(),this._Image89_i(),this.consume_i(),this.function_i(),this.seed_i(),this.petattack_i(),this.gundong0_i(),this.wenzi0_i(),this._Scroller3_i(),this.ConsumeGoods_i(),this.ConsumeGoods_Buy_i(),this.ConsumeGoods_zhongzi_i(),this.ConsumeGoods_Buy0_i(),this.PropsOrder_i(),this._Group2_i(),this.Presented_i(),this.zhifu_i()];
		return t;
	};
	_proto.warehouse_i = function () {
		var t = new eui.Image();
		this.warehouse = t;
		t.height = 36;
		t.source = "cangku@3x(1)_png";
		t.width = 60;
		t.x = 280;
		t.y = 206;
		return t;
	};
	_proto._Image88_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "jux@2x(2)_png";
		t.width = 72;
		t.x = 111;
		t.y = 143;
		return t;
	};
	_proto.prop_money_i = function () {
		var t = new eui.Label();
		this.prop_money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 9.5;
		t.size = 13;
		t.text = "0";
		t.textAlign = "center";
		t.textColor = 0xf3eb50;
		t.verticalAlign = "middle";
		t.width = 59;
		t.x = 117;
		t.y = 147.5;
		return t;
	};
	_proto.PropsRecord_i = function () {
		var t = new eui.Button();
		this.PropsRecord = t;
		t.height = 25;
		t.label = "";
		t.width = 82;
		t.x = 217;
		t.y = 141;
		t.skinName = TheFarmLobbySkin$Skin48;
		return t;
	};
	_proto._Image89_i = function () {
		var t = new eui.Image();
		t.height = 24;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 20;
		t.x = 79;
		t.y = 140;
		return t;
	};
	_proto.consume_i = function () {
		var t = new eui.Image();
		this.consume = t;
		t.height = 36;
		t.source = "xiaohao@3x_png";
		t.width = 60;
		t.x = 36;
		t.y = 206;
		return t;
	};
	_proto.function_i = function () {
		var t = new eui.Image();
		this.function = t;
		t.height = 36;
		t.source = "gongneng@3x_png";
		t.width = 60;
		t.x = 96;
		t.y = 206;
		return t;
	};
	_proto.seed_i = function () {
		var t = new eui.Image();
		this.seed = t;
		t.height = 36;
		t.source = "zhongzi@3x_png";
		t.width = 60;
		t.x = 157;
		t.y = 206;
		return t;
	};
	_proto.petattack_i = function () {
		var t = new eui.Image();
		this.petattack = t;
		t.height = 36;
		t.source = "chongwu@3x_png";
		t.width = 60;
		t.x = 219;
		t.y = 206;
		return t;
	};
	_proto.gundong0_i = function () {
		var t = new eui.Image();
		this.gundong0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "red@3x_png";
		t.width = 213;
		t.x = 79;
		t.y = 174;
		return t;
	};
	_proto.wenzi0_i = function () {
		var t = new eui.Label();
		this.wenzi0 = t;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 12;
		t.text = "恭喜小猪佩奇成功购买了2倍肥料";
		t.x = 94;
		t.y = 200;
		return t;
	};
	_proto._Scroller3_i = function () {
		var t = new eui.Scroller();
		t.height = 312.15;
		t.width = 291.68;
		t.x = 41.16;
		t.y = 249;
		t.viewport = this.WarehouseGoods_i();
		return t;
	};
	_proto.WarehouseGoods_i = function () {
		var t = new eui.Group();
		this.WarehouseGoods = t;
		t.anchorOffsetX = 0;
		t.visible = false;
		t.width = 305;
		t.elementsContent = [this.cry_i()];
		return t;
	};
	_proto.cry_i = function () {
		var t = new eui.Group();
		this.cry = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 198.48;
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 272.91;
		t.x = 10.28;
		t.y = 37.67;
		t.elementsContent = [this._Label18_i()];
		return t;
	};
	_proto._Label18_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58.79;
		t.size = 24;
		t.text = "背包好空啊(;´༎ຶД༎ຶ`)";
		t.textColor = 0x000000;
		t.width = 217.36;
		t.x = 47.33;
		t.y = 84.08;
		return t;
	};
	_proto.ConsumeGoods_i = function () {
		var t = new eui.Group();
		this.ConsumeGoods = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 321.15;
		t.touchThrough = true;
		t.visible = false;
		t.width = 291.68;
		t.x = 43.16;
		t.y = 249;
		return t;
	};
	_proto.ConsumeGoods_Buy_i = function () {
		var t = new eui.Group();
		this.ConsumeGoods_Buy = t;
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image90_i(),this._Image91_i(),this._Image92_i(),this.ConsumeGoods_close_i(),this.SPTP_i(),this._Image93_i(),this.buy_text_i(),this.ConsumeGoods_Subtract_i(),this.ConsumeGoods_Plus_i(),this.PurchaseQuantity_i(),this.presented_i(),this.purchase0_i(),this._Image94_i(),this._Image95_i(),this._Image96_i(),this.price_i()];
		return t;
	};
	_proto._Image90_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image91_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 315;
		t.source = "qwe3x_png";
		t.width = 300;
		t.x = 39;
		t.y = 179;
		return t;
	};
	_proto._Image92_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 84;
		t.source = "juxingDJDHGM@3x_png";
		t.width = 259;
		t.x = 58;
		t.y = 244;
		return t;
	};
	_proto.ConsumeGoods_close_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_close = t;
		t.height = 30;
		t.label = "";
		t.width = 30;
		t.x = 314;
		t.y = 174;
		t.skinName = TheFarmLobbySkin$Skin49;
		return t;
	};
	_proto.SPTP_i = function () {
		var t = new eui.Image();
		this.SPTP = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 68;
		t.source = "GMBJbeijing@3x_png";
		t.width = 72;
		t.x = 90;
		t.y = 340;
		return t;
	};
	_proto._Image93_i = function () {
		var t = new eui.Image();
		t.height = 24;
		t.source = "juxing(1)_png";
		t.width = 80;
		t.x = 191;
		t.y = 351;
		return t;
	};
	_proto.buy_text_i = function () {
		var t = new eui.Label();
		this.buy_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.background = false;
		t.backgroundColor = 0xf2e6e6;
		t.height = 56;
		t.multiline = true;
		t.size = 14;
		t.text = "增加";
		t.textColor = 0x000000;
		t.width = 222;
		t.x = 71;
		t.y = 254;
		return t;
	};
	_proto.ConsumeGoods_Subtract_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Subtract = t;
		t.height = 31;
		t.label = "";
		t.width = 31;
		t.x = 178;
		t.y = 348;
		t.skinName = TheFarmLobbySkin$Skin50;
		return t;
	};
	_proto.ConsumeGoods_Plus_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Plus = t;
		t.height = 31;
		t.label = "";
		t.width = 31;
		t.x = 251;
		t.y = 348;
		t.skinName = TheFarmLobbySkin$Skin51;
		return t;
	};
	_proto.PurchaseQuantity_i = function () {
		var t = new eui.Label();
		this.PurchaseQuantity = t;
		t.height = 14;
		t.size = 18;
		t.text = "1";
		t.x = 224;
		t.y = 356;
		return t;
	};
	_proto.presented_i = function () {
		var t = new eui.Button();
		this.presented = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 90;
		t.y = 429;
		t.skinName = TheFarmLobbySkin$Skin52;
		return t;
	};
	_proto.purchase0_i = function () {
		var t = new eui.Button();
		this.purchase0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 199;
		t.y = 429;
		t.skinName = TheFarmLobbySkin$Skin53;
		return t;
	};
	_proto._Image94_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "goumai_png";
		t.width = 214;
		t.x = 86;
		t.y = 162;
		return t;
	};
	_proto._Image95_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 13;
		t.source = "xianjinfuli_png";
		t.width = 18;
		t.x = 231;
		t.y = 387;
		return t;
	};
	_proto._Image96_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.source = "jiazhi@3x_png";
		t.width = 35;
		t.x = 182;
		t.y = 384;
		return t;
	};
	_proto.price_i = function () {
		var t = new eui.Label();
		this.price = t;
		t.height = 12;
		t.size = 14;
		t.text = "0元";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.x = 255;
		t.y = 387;
		return t;
	};
	_proto.ConsumeGoods_zhongzi_i = function () {
		var t = new eui.Group();
		this.ConsumeGoods_zhongzi = t;
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image97_i(),this._Image98_i(),this._Image99_i(),this.ConsumeGoods_close_zhongzi_i(),this.SPTP_zhongzi_i(),this._Image100_i(),this.buy_text_zhongzi_i(),this.ConsumeGoods_Subtract_zhongzi_i(),this.ConsumeGoods_Plus_zhongzi_i(),this.PurchaseQuantity_zhongzi_i(),this.presented_zhongzi_i(),this.purchase_zhongzi_i(),this._Image101_i(),this._Image102_i(),this._Image103_i(),this.price_zhongzi_i()];
		return t;
	};
	_proto._Image97_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image98_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 315;
		t.source = "qwe3x_png";
		t.width = 300;
		t.x = 39;
		t.y = 179;
		return t;
	};
	_proto._Image99_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 84;
		t.source = "juxingDJDHGM@3x_png";
		t.width = 259;
		t.x = 58;
		t.y = 244;
		return t;
	};
	_proto.ConsumeGoods_close_zhongzi_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_close_zhongzi = t;
		t.height = 30;
		t.label = "";
		t.width = 30;
		t.x = 314;
		t.y = 174;
		t.skinName = TheFarmLobbySkin$Skin54;
		return t;
	};
	_proto.SPTP_zhongzi_i = function () {
		var t = new eui.Image();
		this.SPTP_zhongzi = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 68;
		t.source = "GMBJbeijing@3x_png";
		t.width = 72;
		t.x = 90;
		t.y = 340;
		return t;
	};
	_proto._Image100_i = function () {
		var t = new eui.Image();
		t.height = 24;
		t.source = "juxing(1)_png";
		t.width = 80;
		t.x = 191;
		t.y = 352;
		return t;
	};
	_proto.buy_text_zhongzi_i = function () {
		var t = new eui.Label();
		this.buy_text_zhongzi = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.background = false;
		t.backgroundColor = 0xF2E6E6;
		t.height = 56;
		t.multiline = true;
		t.size = 14;
		t.text = "增加";
		t.textColor = 0x000000;
		t.width = 222;
		t.x = 71;
		t.y = 254;
		return t;
	};
	_proto.ConsumeGoods_Subtract_zhongzi_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Subtract_zhongzi = t;
		t.height = 31;
		t.label = "";
		t.width = 31;
		t.x = 178;
		t.y = 348;
		t.skinName = TheFarmLobbySkin$Skin55;
		return t;
	};
	_proto.ConsumeGoods_Plus_zhongzi_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Plus_zhongzi = t;
		t.height = 31;
		t.label = "";
		t.width = 31;
		t.x = 251;
		t.y = 348;
		t.skinName = TheFarmLobbySkin$Skin56;
		return t;
	};
	_proto.PurchaseQuantity_zhongzi_i = function () {
		var t = new eui.Label();
		this.PurchaseQuantity_zhongzi = t;
		t.height = 14;
		t.size = 18;
		t.text = "1";
		t.x = 224;
		t.y = 356;
		return t;
	};
	_proto.presented_zhongzi_i = function () {
		var t = new eui.Button();
		this.presented_zhongzi = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 90;
		t.y = 429;
		t.skinName = TheFarmLobbySkin$Skin57;
		return t;
	};
	_proto.purchase_zhongzi_i = function () {
		var t = new eui.Button();
		this.purchase_zhongzi = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 199;
		t.y = 429;
		t.skinName = TheFarmLobbySkin$Skin58;
		return t;
	};
	_proto._Image101_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "goumai_png";
		t.width = 214;
		t.x = 86;
		t.y = 162;
		return t;
	};
	_proto._Image102_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 13.5;
		t.x = 226.5;
		t.y = 384;
		return t;
	};
	_proto._Image103_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.source = "jiazhi@3x_png";
		t.width = 35;
		t.x = 182;
		t.y = 385;
		return t;
	};
	_proto.price_zhongzi_i = function () {
		var t = new eui.Label();
		this.price_zhongzi = t;
		t.height = 12;
		t.size = 14;
		t.text = "0个";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.x = 255;
		t.y = 387;
		return t;
	};
	_proto.ConsumeGoods_Buy0_i = function () {
		var t = new eui.Group();
		this.ConsumeGoods_Buy0 = t;
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image104_i(),this._Image105_i(),this._Image106_i(),this.ConsumeGoods_close0_i(),this.DJSYJMSPTB_i(),this.buy_text0_i(),this.purchase1_i(),this._Image107_i(),this.shuliang_i(),this._Label19_i()];
		return t;
	};
	_proto._Image104_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image105_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 288;
		t.source = "beijingDJSY@3x_png";
		t.width = 304;
		t.x = 36;
		t.y = 184;
		return t;
	};
	_proto._Image106_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 87;
		t.source = "juxingDJDHGM@3x_png";
		t.width = 189;
		t.x = 132;
		t.y = 270;
		return t;
	};
	_proto.ConsumeGoods_close0_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_close0 = t;
		t.height = 30;
		t.label = "";
		t.width = 30;
		t.x = 314;
		t.y = 174;
		t.skinName = TheFarmLobbySkin$Skin59;
		return t;
	};
	_proto.DJSYJMSPTB_i = function () {
		var t = new eui.Image();
		this.DJSYJMSPTB = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 90;
		t.source = "baijinchutou@3x_png";
		t.width = 75;
		t.x = 48;
		t.y = 264;
		return t;
	};
	_proto.buy_text0_i = function () {
		var t = new eui.Label();
		this.buy_text0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.background = false;
		t.backgroundColor = 0xF2E6E6;
		t.height = 56;
		t.multiline = true;
		t.size = 13;
		t.text = "增加";
		t.textColor = 0x000000;
		t.width = 162;
		t.x = 144;
		t.y = 286;
		return t;
	};
	_proto.purchase1_i = function () {
		var t = new eui.Button();
		this.purchase1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 35.5;
		t.label = "";
		t.width = 80.5;
		t.x = 147;
		t.y = 386.5;
		t.skinName = TheFarmLobbySkin$Skin60;
		return t;
	};
	_proto._Image107_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "DJSY@3x_png";
		t.width = 214;
		t.x = 86;
		t.y = 169;
		return t;
	};
	_proto.shuliang_i = function () {
		var t = new eui.Label();
		this.shuliang = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "KaiTi";
		t.height = 39;
		t.size = 11;
		t.text = "  × 2";
		t.textAlign = "center";
		t.textColor = 0x00f4ff;
		t.verticalAlign = "middle";
		t.width = 112;
		t.x = 37;
		t.y = 320;
		return t;
	};
	_proto._Label19_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.fontFamily = "KaiTi";
		t.size = 11;
		t.text = "库存";
		t.textColor = 0x000000;
		t.x = 58;
		t.y = 334;
		return t;
	};
	_proto.PropsOrder_i = function () {
		var t = new eui.Group();
		this.PropsOrder = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image108_i(),this._Image109_i(),this.PropsOrder_close_i(),this._Image110_i(),this.Indent_i()];
		return t;
	};
	_proto._Image108_i = function () {
		var t = new eui.Image();
		t.height = 900;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image109_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.height = 369;
		t.source = "qwe3x_png";
		t.width = 304;
		t.x = 36;
		t.y = 171;
		return t;
	};
	_proto.PropsOrder_close_i = function () {
		var t = new eui.Button();
		this.PropsOrder_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 164;
		t.skinName = TheFarmLobbySkin$Skin61;
		return t;
	};
	_proto._Image110_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "daojudingdan@3x(2)_png";
		t.width = 214;
		t.x = 86;
		t.y = 155;
		return t;
	};
	_proto.Indent_i = function () {
		var t = new eui.Scroller();
		this.Indent = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 294.85;
		t.width = 262;
		t.x = 56.5;
		t.y = 218;
		t.viewport = this.OrderForm_i();
		return t;
	};
	_proto.OrderForm_i = function () {
		var t = new eui.Group();
		this.OrderForm = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 381.51;
		t.width = 476.73;
		return t;
	};
	_proto._Group2_i = function () {
		var t = new eui.Group();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image111_i(),this._Image112_i(),this._Image113_i(),this.PropsFor_close0_i(),this._Image114_i(),this.prop_money0_i(),this.PropsRecord0_i(),this._Image115_i(),this._Image116_i(),this._Image117_i(),this._Label20_i(),this._Label21_i(),this._Label22_i(),this._Image118_i(),this.ConsumeGoods_Subtract_zhongzi0_i(),this.ConsumeGoods_Plus_zhongzi0_i(),this.PurchaseQuantity_zhongzi0_i(),this.presented_zhongzi0_i(),this.purchase_zhongzi0_i(),this.libaoXQ_i()];
		return t;
	};
	_proto._Image111_i = function () {
		var t = new eui.Image();
		t.height = 548.79;
		t.source = "beijing123@3x_png";
		t.width = 353;
		t.x = 15;
		t.y = 44;
		return t;
	};
	_proto._Image112_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75;
		t.source = "fenge@3x_png";
		t.width = 329;
		t.x = 23;
		t.y = 86;
		return t;
	};
	_proto._Image113_i = function () {
		var t = new eui.Image();
		t.height = 40;
		t.source = "xiangqing@3x_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto.PropsFor_close0_i = function () {
		var t = new eui.Button();
		this.PropsFor_close0 = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 312;
		t.y = 56;
		t.skinName = TheFarmLobbySkin$Skin62;
		return t;
	};
	_proto._Image114_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "jux@2x(2)_png";
		t.width = 72;
		t.x = 111;
		t.y = 107;
		return t;
	};
	_proto.prop_money0_i = function () {
		var t = new eui.Label();
		this.prop_money0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 9.5;
		t.size = 13;
		t.text = "0";
		t.textAlign = "center";
		t.textColor = 0xF3EB50;
		t.verticalAlign = "middle";
		t.width = 59;
		t.x = 117;
		t.y = 111.5;
		return t;
	};
	_proto.PropsRecord0_i = function () {
		var t = new eui.Button();
		this.PropsRecord0 = t;
		t.height = 25;
		t.label = "";
		t.width = 82;
		t.x = 217;
		t.y = 105;
		t.skinName = TheFarmLobbySkin$Skin63;
		return t;
	};
	_proto._Image115_i = function () {
		var t = new eui.Image();
		t.height = 24;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 20;
		t.x = 84;
		t.y = 104;
		return t;
	};
	_proto._Image116_i = function () {
		var t = new eui.Image();
		t.height = 120;
		t.source = "HSTP_png";
		t.width = 110;
		t.x = 133;
		t.y = 155;
		return t;
	};
	_proto._Image117_i = function () {
		var t = new eui.Image();
		t.height = 104;
		t.source = "SWZZXQXX_png";
		t.width = 309;
		t.x = 33;
		t.y = 284;
		return t;
	};
	_proto._Label20_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 77;
		t.size = 14;
		t.text = "Label";
		t.textColor = 0x024d44;
		t.width = 274;
		t.x = 51;
		t.y = 297;
		return t;
	};
	_proto._Label21_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 16;
		t.text = "种子价格：";
		t.textColor = 0x002a25;
		t.x = 120;
		t.y = 408;
		return t;
	};
	_proto._Label22_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 16;
		t.text = "支付价格：";
		t.textColor = 0x002a25;
		t.x = 120;
		t.y = 444;
		return t;
	};
	_proto._Image118_i = function () {
		var t = new eui.Image();
		t.height = 24;
		t.source = "juxing(1)_png";
		t.width = 80;
		t.x = 141;
		t.y = 475;
		return t;
	};
	_proto.ConsumeGoods_Subtract_zhongzi0_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Subtract_zhongzi0 = t;
		t.height = 31;
		t.label = "";
		t.width = 31;
		t.x = 123;
		t.y = 471;
		t.skinName = TheFarmLobbySkin$Skin64;
		return t;
	};
	_proto.ConsumeGoods_Plus_zhongzi0_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Plus_zhongzi0 = t;
		t.height = 31;
		t.label = "";
		t.width = 31;
		t.x = 201;
		t.y = 471;
		t.skinName = TheFarmLobbySkin$Skin65;
		return t;
	};
	_proto.PurchaseQuantity_zhongzi0_i = function () {
		var t = new eui.Label();
		this.PurchaseQuantity_zhongzi0 = t;
		t.height = 14;
		t.size = 18;
		t.text = "1";
		t.x = 174;
		t.y = 479;
		return t;
	};
	_proto.presented_zhongzi0_i = function () {
		var t = new eui.Button();
		this.presented_zhongzi0 = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 86;
		t.y = 517;
		t.skinName = TheFarmLobbySkin$Skin66;
		return t;
	};
	_proto.purchase_zhongzi0_i = function () {
		var t = new eui.Button();
		this.purchase_zhongzi0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 195;
		t.y = 517;
		t.skinName = TheFarmLobbySkin$Skin67;
		return t;
	};
	_proto.libaoXQ_i = function () {
		var t = new eui.Image();
		this.libaoXQ = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 35;
		t.source = "libaoxiangqing@3x_png";
		t.width = 85;
		t.x = 245;
		t.y = 238;
		return t;
	};
	_proto.Presented_i = function () {
		var t = new eui.Group();
		this.Presented = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image119_i(),this._Image120_i(),this.Presented_close_i(),this._Image121_i(),this._Scroller4_i()];
		return t;
	};
	_proto._Image119_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image120_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 395;
		t.source = "qwe3x_png";
		t.width = 300;
		t.x = 38;
		t.y = 143;
		return t;
	};
	_proto.Presented_close_i = function () {
		var t = new eui.Button();
		this.Presented_close = t;
		t.height = 30;
		t.label = "";
		t.width = 30;
		t.x = 312;
		t.y = 137;
		t.skinName = TheFarmLobbySkin$Skin68;
		return t;
	};
	_proto._Image121_i = function () {
		var t = new eui.Image();
		t.height = 58;
		t.source = "zengsong@3xBT_png";
		t.width = 214;
		t.x = 86;
		t.y = 127;
		return t;
	};
	_proto._Scroller4_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetY = 0;
		t.height = 322;
		t.width = 256;
		t.x = 59;
		t.y = 191;
		t.viewport = this.PresentedJM_i();
		return t;
	};
	_proto.PresentedJM_i = function () {
		var t = new eui.Group();
		this.PresentedJM = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 209;
		t.width = 206;
		return t;
	};
	_proto.zhifu_i = function () {
		var t = new eui.Group();
		this.zhifu = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.zhifu_bj_i(),this._Image122_i(),this._Label23_i(),this.weixin1_i(),this.zhifubao1_i(),this.weixin_i(),this.zhifubao_i()];
		return t;
	};
	_proto.zhifu_bj_i = function () {
		var t = new eui.Image();
		this.zhifu_bj = t;
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image122_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 124;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.width = 375;
		t.x = 0;
		t.y = 543;
		return t;
	};
	_proto._Label23_i = function () {
		var t = new eui.Label();
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "请选择支付方式";
		t.textColor = 0x333333;
		t.x = 11.5;
		t.y = 554;
		return t;
	};
	_proto.weixin1_i = function () {
		var t = new eui.Image();
		this.weixin1 = t;
		t.height = 30;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "weixin_png";
		t.width = 30;
		t.x = 30;
		t.y = 581;
		return t;
	};
	_proto.zhifubao1_i = function () {
		var t = new eui.Image();
		this.zhifubao1 = t;
		t.height = 30;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "zhifubao_png";
		t.width = 30;
		t.x = 30;
		t.y = 629;
		return t;
	};
	_proto.weixin_i = function () {
		var t = new eui.Label();
		this.weixin = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.size = 20;
		t.text = "微信支付";
		t.textAlign = "center";
		t.textColor = 0x333333;
		t.verticalAlign = "middle";
		t.width = 320;
		t.x = 25;
		t.y = 579;
		return t;
	};
	_proto.zhifubao_i = function () {
		var t = new eui.Label();
		this.zhifubao = t;
		t.anchorOffsetX = 0;
		t.height = 40;
		t.size = 20;
		t.text = "支付宝支付";
		t.textAlign = "center";
		t.textColor = 0x333333;
		t.verticalAlign = "middle";
		t.width = 320;
		t.x = 25;
		t.y = 624;
		return t;
	};
	_proto.ConversionGoods_i = function () {
		var t = new eui.Group();
		this.ConversionGoods = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.Change_i(),this.tu1_i(),this.tu2_i(),this.tu3_i(),this.secret1_i(),this.secret2_i(),this.secret3_i(),this.gundong2_i(),this.wenzi2_i(),this.conversion_presenter_1_i(),this.conversion_presenter_2_i(),this.conversion_presenter_3_i(),this._Image123_i(),this.Conversion_money_i(),this._Image124_i(),this._Image125_i(),this.Modellpause_i(),this.Bill_i(),this.MyOrder_i(),this.Discuss_i(),this.ChangeTheOrder_i(),this.Select_category_i(),this.address_i(),this.deficiency_i(),this.succeed_i(),this.Tishi_i(),this.discuss_i(),this.hint3_i()];
		return t;
	};
	_proto.Change_i = function () {
		var t = new eui.Button();
		this.Change = t;
		t.height = 25;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 82;
		t.x = 217;
		t.y = 141;
		t.skinName = TheFarmLobbySkin$Skin69;
		return t;
	};
	_proto.tu1_i = function () {
		var t = new eui.Image();
		this.tu1 = t;
		t.height = 110;
		t.source = "mianmo_png";
		t.width = 100;
		t.x = 33;
		t.y = 209;
		return t;
	};
	_proto.tu2_i = function () {
		var t = new eui.Image();
		this.tu2 = t;
		t.height = 110;
		t.source = "mianmo_png";
		t.width = 100;
		t.x = 138;
		t.y = 209;
		return t;
	};
	_proto.tu3_i = function () {
		var t = new eui.Image();
		this.tu3 = t;
		t.height = 110;
		t.source = "mianmo_png";
		t.width = 100;
		t.x = 243;
		t.y = 209;
		return t;
	};
	_proto.secret1_i = function () {
		var t = new eui.Image();
		this.secret1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 102.4;
		t.source = "JX35@3x_png";
		t.width = 91.8;
		t.x = 36.4;
		t.y = 212.6;
		return t;
	};
	_proto.secret2_i = function () {
		var t = new eui.Image();
		this.secret2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 101.6;
		t.source = "JX35@3x_png";
		t.width = 92;
		t.x = 142;
		t.y = 213;
		return t;
	};
	_proto.secret3_i = function () {
		var t = new eui.Image();
		this.secret3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 102.4;
		t.source = "JX35@3x_png";
		t.width = 91.6;
		t.x = 247.4;
		t.y = 212.6;
		return t;
	};
	_proto.gundong2_i = function () {
		var t = new eui.Image();
		this.gundong2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "red_png";
		t.width = 213;
		t.x = 79;
		t.y = 174;
		return t;
	};
	_proto.wenzi2_i = function () {
		var t = new eui.Label();
		this.wenzi2 = t;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 12;
		t.text = "恭喜小猪佩奇成功购买了2倍肥料";
		t.x = 94;
		t.y = 200;
		return t;
	};
	_proto.conversion_presenter_1_i = function () {
		var t = new eui.Button();
		this.conversion_presenter_1 = t;
		t.enabled = false;
		t.height = 36;
		t.label = "";
		t.width = 88;
		t.x = 39;
		t.y = 319;
		t.skinName = TheFarmLobbySkin$Skin70;
		return t;
	};
	_proto.conversion_presenter_2_i = function () {
		var t = new eui.Button();
		this.conversion_presenter_2 = t;
		t.enabled = false;
		t.height = 36;
		t.label = "";
		t.width = 88;
		t.x = 141;
		t.y = 319;
		t.skinName = TheFarmLobbySkin$Skin71;
		return t;
	};
	_proto.conversion_presenter_3_i = function () {
		var t = new eui.Button();
		this.conversion_presenter_3 = t;
		t.enabled = false;
		t.height = 36;
		t.label = "";
		t.width = 88;
		t.x = 248;
		t.y = 319;
		t.skinName = TheFarmLobbySkin$Skin72;
		return t;
	};
	_proto._Image123_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.source = "jux@2x(2)_png";
		t.width = 72;
		t.x = 111;
		t.y = 143;
		return t;
	};
	_proto.Conversion_money_i = function () {
		var t = new eui.Label();
		this.Conversion_money = t;
		t.height = 9.5;
		t.size = 13;
		t.text = "钱数";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 59;
		t.x = 117;
		t.y = 147.5;
		return t;
	};
	_proto._Image124_i = function () {
		var t = new eui.Image();
		t.height = 24;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 20;
		t.x = 79;
		t.y = 140;
		return t;
	};
	_proto._Image125_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 35;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "juxingDJDHDDBJ@3x_png";
		t.width = 307;
		t.x = 34;
		t.y = 367;
		return t;
	};
	_proto.Modellpause_i = function () {
		var t = new eui.Image();
		this.Modellpause = t;
		t.height = 37;
		t.source = "shaitu@3x_png";
		t.width = 100;
		t.x = 124;
		t.y = 368;
		return t;
	};
	_proto.Bill_i = function () {
		var t = new eui.Image();
		this.Bill = t;
		t.height = 40;
		t.source = "wodedingdan@3x_png";
		t.width = 100;
		t.x = 34;
		t.y = 367;
		return t;
	};
	_proto.MyOrder_i = function () {
		var t = new eui.Group();
		this.MyOrder = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 177.27;
		t.width = 368.19;
		t.x = 6.05;
		t.y = 408;
		t.elementsContent = [this.All_i(),this.Obligation_i(),this.ForShipping_i(),this.HasBeenShipped_i(),this.Indent0_i()];
		return t;
	};
	_proto.All_i = function () {
		var t = new eui.Image();
		this.All = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 46;
		t.source = "quanbu@3x_png";
		t.width = 80;
		t.x = 29.69;
		t.y = 0;
		return t;
	};
	_proto.Obligation_i = function () {
		var t = new eui.Image();
		this.Obligation = t;
		t.height = 32;
		t.source = "fukuan@3x_png";
		t.width = 80;
		t.x = 104.69;
		t.y = 0;
		return t;
	};
	_proto.ForShipping_i = function () {
		var t = new eui.Image();
		this.ForShipping = t;
		t.height = 32;
		t.source = "peisong@2x(1)_png";
		t.width = 80;
		t.x = 177.69;
		t.y = 0;
		return t;
	};
	_proto.HasBeenShipped_i = function () {
		var t = new eui.Image();
		this.HasBeenShipped = t;
		t.height = 32;
		t.source = "peisong@3x_png";
		t.width = 80;
		t.x = 251.69;
		t.y = 0;
		return t;
	};
	_proto.Indent0_i = function () {
		var t = new eui.Scroller();
		this.Indent0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 102;
		t.width = 300;
		t.x = 32.39;
		t.y = 46.11;
		t.viewport = this.indent_particulars_i();
		return t;
	};
	_proto.indent_particulars_i = function () {
		var t = new eui.Group();
		this.indent_particulars = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 111;
		t.width = 266.58;
		t.x = -1;
		t.y = -1;
		return t;
	};
	_proto.Discuss_i = function () {
		var t = new eui.Scroller();
		this.Discuss = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 148;
		t.visible = false;
		t.width = 289;
		t.x = 43.43;
		t.y = 407.32;
		t.viewport = this.DiscussX_i();
		return t;
	};
	_proto.DiscussX_i = function () {
		var t = new eui.Group();
		this.DiscussX = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 159;
		t.width = 371.83;
		t.y = -3.03;
		return t;
	};
	_proto.ChangeTheOrder_i = function () {
		var t = new eui.Group();
		this.ChangeTheOrder = t;
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image126_i(),this._Image127_i(),this._Image128_i(),this.ChangeTheOrder_close_i(),this._Scroller5_i()];
		return t;
	};
	_proto._Image126_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image127_i = function () {
		var t = new eui.Image();
		t.anchorOffsetY = 0;
		t.height = 369;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing3_png";
		t.width = 306;
		t.x = 36;
		t.y = 166.5;
		return t;
	};
	_proto._Image128_i = function () {
		var t = new eui.Image();
		t.height = 58;
		t.source = "daojudingdan@3x(1)_png";
		t.width = 214;
		t.x = 86;
		t.y = 149.68;
		return t;
	};
	_proto.ChangeTheOrder_close_i = function () {
		var t = new eui.Button();
		this.ChangeTheOrder_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 164;
		t.skinName = TheFarmLobbySkin$Skin73;
		return t;
	};
	_proto._Scroller5_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 290.24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 262;
		t.x = 57.64;
		t.y = 215.26;
		t.viewport = this.exchange_i();
		return t;
	};
	_proto.exchange_i = function () {
		var t = new eui.Group();
		this.exchange = t;
		t.anchorOffsetY = 0;
		t.height = 376.91;
		return t;
	};
	_proto.Select_category_i = function () {
		var t = new eui.Group();
		this.Select_category = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image129_i(),this._Image130_i(),this.Xtu_i(),this.Select_category_close_i(),this._Image131_i(),this._Image132_i(),this.Xname_i(),this._Label24_i(),this.Xshu_i(),this.tishi_i()];
		return t;
	};
	_proto._Image129_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image130_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 340;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing3_png";
		t.width = 300;
		t.x = 38;
		t.y = 143;
		return t;
	};
	_proto.Xtu_i = function () {
		var t = new eui.Image();
		this.Xtu = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 77;
		t.source = "SWZZXQXX_png";
		t.width = 268;
		t.x = 53;
		t.y = 218;
		return t;
	};
	_proto.Select_category_close_i = function () {
		var t = new eui.Button();
		this.Select_category_close = t;
		t.height = 32;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 32;
		t.x = 312;
		t.y = 137;
		t.skinName = TheFarmLobbySkin$Skin74;
		return t;
	};
	_proto._Image131_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pinlei_png";
		t.width = 214;
		t.x = 86;
		t.y = 127;
		return t;
	};
	_proto._Image132_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 80;
		t.source = "DHLBXZ_png";
		t.touchEnabled = false;
		t.width = 80;
		t.x = 60;
		t.y = 211;
		return t;
	};
	_proto.Xname_i = function () {
		var t = new eui.Label();
		this.Xname = t;
		t.size = 24;
		t.text = "Label";
		t.textColor = 0x000000;
		t.touchEnabled = false;
		t.x = 148;
		t.y = 227;
		return t;
	};
	_proto._Label24_i = function () {
		var t = new eui.Label();
		t.size = 13;
		t.text = "可兑换数量：";
		t.textColor = 0x146776;
		t.touchEnabled = false;
		t.x = 150;
		t.y = 268;
		return t;
	};
	_proto.Xshu_i = function () {
		var t = new eui.Label();
		this.Xshu = t;
		t.anchorOffsetX = 0;
		t.size = 13;
		t.text = "Label";
		t.textColor = 0x146776;
		t.touchEnabled = false;
		t.x = 234;
		t.y = 268;
		return t;
	};
	_proto.tishi_i = function () {
		var t = new eui.Button();
		this.tishi = t;
		t.height = 30;
		t.label = "";
		t.width = 30;
		t.x = 286;
		t.y = 255;
		t.skinName = TheFarmLobbySkin$Skin75;
		return t;
	};
	_proto.address_i = function () {
		var t = new eui.Group();
		this.address = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image133_i(),this._Image134_i(),this.receiver_address_i(),this.receiver_name_i(),this._Image135_i(),this.address_close_i(),this._Label25_i(),this._Label26_i(),this._Label27_i(),this.receiver_number_i(),this.address_yes_i()];
		return t;
	};
	_proto._Image133_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image134_i = function () {
		var t = new eui.Image();
		t.height = 334;
		t.source = "beijing3_png";
		t.width = 294;
		t.x = 41;
		t.y = 142;
		return t;
	};
	_proto.receiver_address_i = function () {
		var t = new eui.TextInput();
		this.receiver_address = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 95;
		t.prompt = "请填写详细收货地址";
		t.width = 176;
		t.x = 133;
		t.y = 303;
		return t;
	};
	_proto.receiver_name_i = function () {
		var t = new eui.TextInput();
		this.receiver_name = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 26;
		t.prompt = "请填写收货人姓名";
		t.width = 176;
		t.x = 133;
		t.y = 216;
		return t;
	};
	_proto._Image135_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "tishi_png";
		t.width = 214;
		t.x = 86;
		t.y = 120;
		return t;
	};
	_proto.address_close_i = function () {
		var t = new eui.Button();
		this.address_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 136;
		t.skinName = TheFarmLobbySkin$Skin76;
		return t;
	};
	_proto._Label25_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 15;
		t.text = "联系人";
		t.textColor = 0x013952;
		t.x = 66;
		t.y = 227;
		return t;
	};
	_proto._Label26_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 15;
		t.text = "联系电话";
		t.textColor = 0x013952;
		t.x = 66;
		t.y = 268;
		return t;
	};
	_proto._Label27_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 15;
		t.text = "联系地址";
		t.textColor = 0x013952;
		t.x = 66;
		t.y = 311;
		return t;
	};
	_proto.receiver_number_i = function () {
		var t = new eui.TextInput();
		this.receiver_number = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 26;
		t.maxChars = 11;
		t.prompt = "请填写收货人联系方式";
		t.restrict = "\"0-9\"";
		t.width = 176;
		t.x = 133;
		t.y = 260;
		return t;
	};
	_proto.address_yes_i = function () {
		var t = new eui.Button();
		this.address_yes = t;
		t.height = 36;
		t.label = "";
		t.width = 76;
		t.x = 150;
		t.y = 416;
		t.skinName = TheFarmLobbySkin$Skin77;
		return t;
	};
	_proto.deficiency_i = function () {
		var t = new eui.Group();
		this.deficiency = t;
		t.anchorOffsetX = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image136_i(),this._Image137_i(),this.succeed_close_i(),this.deficiency_text_i(),this._Image138_i(),this.shut1_i()];
		return t;
	};
	_proto._Image136_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image137_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 225;
		t.source = "beijingBKD@3x_png";
		t.width = 300;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto.succeed_close_i = function () {
		var t = new eui.Button();
		this.succeed_close = t;
		t.height = 35;
		t.label = "";
		t.width = 35;
		t.x = 308;
		t.y = 208;
		t.skinName = TheFarmLobbySkin$Skin78;
		return t;
	};
	_proto.deficiency_text_i = function () {
		var t = new eui.Label();
		this.deficiency_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 158;
		t.size = 20;
		t.text = "      抱歉您的葫芦币不足";
		t.textColor = 0x000000;
		t.width = 261.6;
		t.x = 52;
		t.y = 259;
		return t;
	};
	_proto._Image138_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.source = "tishi_png";
		t.width = 204;
		t.x = 87;
		t.y = 200;
		return t;
	};
	_proto.shut1_i = function () {
		var t = new eui.Button();
		this.shut1 = t;
		t.height = 30;
		t.label = "";
		t.width = 65;
		t.x = 155;
		t.y = 381;
		t.skinName = TheFarmLobbySkin$Skin79;
		return t;
	};
	_proto.succeed_i = function () {
		var t = new eui.Group();
		this.succeed = t;
		t.anchorOffsetX = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image139_i(),this._Image140_i(),this._Label28_i(),this._Image141_i(),this.shut2_i()];
		return t;
	};
	_proto._Image139_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image140_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.height = 225;
		t.source = "beijingBKD@3x_png";
		t.width = 300;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto._Label28_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 145;
		t.multiline = true;
		t.size = 20;
		t.text = "       恭喜您兑换成功，我们马上开始发货，请耐心等待";
		t.textColor = 0x000000;
		t.width = 250.67;
		t.x = 60;
		t.y = 265;
		return t;
	};
	_proto._Image141_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.source = "tishi_png";
		t.width = 204;
		t.x = 87;
		t.y = 200;
		return t;
	};
	_proto.shut2_i = function () {
		var t = new eui.Button();
		this.shut2 = t;
		t.height = 30;
		t.label = "";
		t.width = 65;
		t.x = 155;
		t.y = 376;
		t.skinName = TheFarmLobbySkin$Skin80;
		return t;
	};
	_proto.Tishi_i = function () {
		var t = new eui.Group();
		this.Tishi = t;
		t.anchorOffsetX = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image142_i(),this._Image143_i(),this.tishi_text_i(),this._Image144_i(),this.shut3_i()];
		return t;
	};
	_proto._Image142_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image143_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.height = 225;
		t.source = "beijingBKD@3x_png";
		t.width = 300;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto.tishi_text_i = function () {
		var t = new eui.Label();
		this.tishi_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 145;
		t.multiline = true;
		t.size = 20;
		t.text = "      农品街商城拿出净利润30%反哺农品街会员，基于每天的商城订单量持续补充农品街面膜库存";
		t.textColor = 0x000000;
		t.width = 250.67;
		t.x = 60;
		t.y = 265;
		return t;
	};
	_proto._Image144_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.source = "tishi_png";
		t.width = 204;
		t.x = 87;
		t.y = 200;
		return t;
	};
	_proto.shut3_i = function () {
		var t = new eui.Button();
		this.shut3 = t;
		t.height = 30;
		t.label = "";
		t.width = 65;
		t.x = 155;
		t.y = 376;
		t.skinName = TheFarmLobbySkin$Skin81;
		return t;
	};
	_proto.discuss_i = function () {
		var t = new eui.Group();
		this.discuss = t;
		t.anchorOffsetX = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image145_i(),this._Image146_i(),this._Image147_i(),this._Image148_i(),this.discuss_text_i(),this.discuss_tu_i(),this.discuss_yes_i(),this._Label29_i(),this.discuss_close_i()];
		return t;
	};
	_proto._Image145_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image146_i = function () {
		var t = new eui.Image();
		t.height = 384;
		t.source = "beijing3_png";
		t.width = 304;
		t.x = 36;
		t.y = 150;
		return t;
	};
	_proto._Image147_i = function () {
		var t = new eui.Image();
		t.height = 58;
		t.source = "shaitu(1)_png";
		t.width = 214;
		t.x = 86;
		t.y = 134;
		return t;
	};
	_proto._Image148_i = function () {
		var t = new eui.Image();
		t.height = 160;
		t.source = "juxingDJDHGM@3x_png";
		t.width = 265;
		t.x = 55;
		t.y = 208;
		return t;
	};
	_proto.discuss_text_i = function () {
		var t = new eui.TextInput();
		this.discuss_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 160;
		t.maxChars = 114;
		t.maxHeight = 160;
		t.maxWidth = 435;
		t.prompt = "请在此输入评论内容（只能输入114个字︿(￣︶￣)︿）";
		t.scaleX = 1;
		t.width = 265;
		t.x = 55;
		t.y = 208;
		return t;
	};
	_proto.discuss_tu_i = function () {
		var t = new eui.Image();
		this.discuss_tu = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 60;
		t.source = "tianjia(1)_png";
		t.touchEnabled = true;
		t.width = 60;
		t.x = 55;
		t.y = 378;
		return t;
	};
	_proto.discuss_yes_i = function () {
		var t = new eui.Button();
		this.discuss_yes = t;
		t.height = 36;
		t.label = "";
		t.width = 77;
		t.x = 147;
		t.y = 472;
		t.skinName = TheFarmLobbySkin$Skin82;
		return t;
	};
	_proto._Label29_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.size = 12;
		t.text = "发表评论即可获得肥料";
		t.textColor = 0xfb3134;
		t.x = 128;
		t.y = 457;
		return t;
	};
	_proto.discuss_close_i = function () {
		var t = new eui.Button();
		this.discuss_close = t;
		t.height = 33;
		t.label = "";
		t.width = 33;
		t.x = 307;
		t.y = 150;
		t.skinName = TheFarmLobbySkin$Skin83;
		return t;
	};
	_proto.hint3_i = function () {
		var t = new eui.Group();
		this.hint3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image149_i(),this._Image150_i(),this._Image151_i(),this._Image152_i(),this.hint3_colse_i(),this.hint3_yes_i()];
		return t;
	};
	_proto._Image149_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 1076.06;
		t.source = "TouMingBeiJing_png";
		t.width = 718.48;
		t.x = -98.94;
		t.y = -229.11;
		return t;
	};
	_proto._Image150_i = function () {
		var t = new eui.Image();
		t.height = 225;
		t.source = "beijingBKD@3x_png";
		t.width = 300;
		t.x = 35;
		t.y = 213;
		return t;
	};
	_proto._Image151_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49;
		t.source = "tishi_png";
		t.width = 204;
		t.x = 87;
		t.y = 200;
		return t;
	};
	_proto._Image152_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.source = "weishangjia@3x_png";
		t.width = 123;
		t.x = 127;
		t.y = 313;
		return t;
	};
	_proto.hint3_colse_i = function () {
		var t = new eui.Button();
		this.hint3_colse = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 307.01;
		t.y = 209;
		t.skinName = TheFarmLobbySkin$Skin84;
		return t;
	};
	_proto.hint3_yes_i = function () {
		var t = new eui.Button();
		this.hint3_yes = t;
		t.height = 25;
		t.label = "";
		t.width = 65;
		t.x = 155;
		t.y = 381;
		t.skinName = TheFarmLobbySkin$Skin85;
		return t;
	};
	_proto.friend_i = function () {
		var t = new eui.Group();
		this.friend = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image153_i(),this._Image154_i(),this.Best_i(),this.Attention_i(),this.Pick_i(),this.REGISTRATION_i(),this.PrivateLetter_i(),this.AddFriend_i(),this._Image155_i(),this.Parameters_i(),this._Image156_i(),this._Image157_i(),this._Image158_i(),this.friend_money_i(),this.friend_close_i(),this.MeetPeople_i(),this.tianjia_i(),this.shanchu_i(),this.Chitchat_i(),this._Scroller6_i(),this.FindMyPhone_i(),this.FriendSet_i()];
		return t;
	};
	_proto._Image153_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image154_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 550.3;
		t.source = "beijing123@3x_png";
		t.width = 353;
		t.x = 15;
		t.y = 47;
		return t;
	};
	_proto.Best_i = function () {
		var t = new eui.Image();
		this.Best = t;
		t.height = 31;
		t.source = "paihang@3x_png";
		t.width = 46;
		t.x = 29;
		t.y = 168;
		return t;
	};
	_proto.Attention_i = function () {
		var t = new eui.Image();
		this.Attention = t;
		t.height = 31;
		t.source = "guanzhu@3x_png";
		t.width = 46;
		t.x = 71;
		t.y = 168;
		return t;
	};
	_proto.Pick_i = function () {
		var t = new eui.Image();
		this.Pick = t;
		t.height = 31;
		t.source = "kezhai@3x_png";
		t.width = 46;
		t.x = 113;
		t.y = 168;
		return t;
	};
	_proto.REGISTRATION_i = function () {
		var t = new eui.Image();
		this.REGISTRATION = t;
		t.height = 31;
		t.source = "shenqing@3x_png";
		t.width = 46;
		t.x = 155;
		t.y = 168;
		return t;
	};
	_proto.PrivateLetter_i = function () {
		var t = new eui.Image();
		this.PrivateLetter = t;
		t.height = 31;
		t.source = "sixin@3x_png";
		t.width = 46;
		t.x = 197;
		t.y = 168;
		return t;
	};
	_proto.AddFriend_i = function () {
		var t = new eui.Button();
		this.AddFriend = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 260;
		t.y = 167;
		t.skinName = TheFarmLobbySkin$Skin86;
		return t;
	};
	_proto._Image155_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75;
		t.source = "fenge@3x_png";
		t.width = 329;
		t.x = 23;
		t.y = 86;
		return t;
	};
	_proto.Parameters_i = function () {
		var t = new eui.Image();
		this.Parameters = t;
		t.anchorOffsetX = 0;
		t.height = 23;
		t.source = "shezhi_png";
		t.width = 61;
		t.x = 240;
		t.y = 100;
		return t;
	};
	_proto._Image156_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "haoyou_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto._Image157_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 31;
		t.source = "huluYXSYHLTB@3x_png";
		t.width = 22;
		t.x = 94;
		t.y = 91;
		return t;
	};
	_proto._Image158_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "jux@2x(2)_png";
		t.width = 78;
		t.x = 122;
		t.y = 99;
		return t;
	};
	_proto.friend_money_i = function () {
		var t = new eui.Label();
		this.friend_money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.size = 13;
		t.text = "0";
		t.textAlign = "center";
		t.textColor = 0xf3eb50;
		t.verticalAlign = "middle";
		t.width = 71;
		t.x = 126;
		t.y = 100;
		return t;
	};
	_proto.friend_close_i = function () {
		var t = new eui.Button();
		this.friend_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 54;
		t.skinName = TheFarmLobbySkin$Skin87;
		return t;
	};
	_proto.MeetPeople_i = function () {
		var t = new eui.Scroller();
		this.MeetPeople = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 340;
		t.width = 315;
		t.x = 30;
		t.y = 216;
		t.viewport = this.figure_i();
		return t;
	};
	_proto.figure_i = function () {
		var t = new eui.Group();
		this.figure = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 535.3;
		t.width = 389;
		t.x = -2;
		return t;
	};
	_proto.tianjia_i = function () {
		var t = new eui.Group();
		this.tianjia = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image159_i(),this.tianjia_close_i(),this._Image160_i(),this.tianjia_text_i(),this.tianjia_quxiao_i(),this.tianjia_queding_i()];
		return t;
	};
	_proto._Image159_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 259;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "qwe3x_png";
		t.width = 304;
		t.x = 36;
		t.y = 212;
		return t;
	};
	_proto.tianjia_close_i = function () {
		var t = new eui.Button();
		this.tianjia_close = t;
		t.height = 30;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 30;
		t.x = 312;
		t.y = 213;
		t.skinName = TheFarmLobbySkin$Skin88;
		return t;
	};
	_proto._Image160_i = function () {
		var t = new eui.Image();
		t.height = 58;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "tishi@3x_png";
		t.width = 214;
		t.x = 83;
		t.y = 188;
		return t;
	};
	_proto.tianjia_text_i = function () {
		var t = new eui.Label();
		this.tianjia_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.size = 16;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 252;
		t.x = 63;
		t.y = 328;
		return t;
	};
	_proto.tianjia_quxiao_i = function () {
		var t = new eui.Button();
		this.tianjia_quxiao = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 90;
		t.y = 394;
		t.skinName = TheFarmLobbySkin$Skin89;
		return t;
	};
	_proto.tianjia_queding_i = function () {
		var t = new eui.Button();
		this.tianjia_queding = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 199;
		t.y = 394;
		t.skinName = TheFarmLobbySkin$Skin90;
		return t;
	};
	_proto.shanchu_i = function () {
		var t = new eui.Group();
		this.shanchu = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image161_i(),this.shanchu_close_i(),this._Image162_i(),this.shanchu_quxiao_i(),this.shanchu_queding_i(),this.shanchu_text_i()];
		return t;
	};
	_proto._Image161_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 259;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "qwe3x_png";
		t.width = 304;
		t.x = 36;
		t.y = 212;
		return t;
	};
	_proto.shanchu_close_i = function () {
		var t = new eui.Button();
		this.shanchu_close = t;
		t.height = 30;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 30;
		t.x = 312;
		t.y = 213;
		t.skinName = TheFarmLobbySkin$Skin91;
		return t;
	};
	_proto._Image162_i = function () {
		var t = new eui.Image();
		t.height = 58;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "tishi@3x_png";
		t.width = 214;
		t.x = 83;
		t.y = 188;
		return t;
	};
	_proto.shanchu_quxiao_i = function () {
		var t = new eui.Button();
		this.shanchu_quxiao = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 90;
		t.y = 394;
		t.skinName = TheFarmLobbySkin$Skin92;
		return t;
	};
	_proto.shanchu_queding_i = function () {
		var t = new eui.Button();
		this.shanchu_queding = t;
		t.height = 34;
		t.label = "";
		t.width = 86;
		t.x = 199;
		t.y = 394;
		t.skinName = TheFarmLobbySkin$Skin93;
		return t;
	};
	_proto.shanchu_text_i = function () {
		var t = new eui.Label();
		this.shanchu_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 18;
		t.size = 16;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 252;
		t.x = 63;
		t.y = 328;
		return t;
	};
	_proto.Chitchat_i = function () {
		var t = new eui.Group();
		this.Chitchat = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.touchThrough = true;
		t.visible = false;
		t.elementsContent = [this._Image163_i(),this.PeopleChat_text_i(),this.PeopleChat_i(),this.ChatTools_i(),this._Image164_i(),this.ChatFrame_i(),this.ChatIcon_i(),this.Send_i(),this.Expression_bar_i(),this.SQBJ_i(),this.FriendsBar0_i()];
		return t;
	};
	_proto._Image163_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 319;
		t.source = "juxingt_png";
		t.width = 310;
		t.x = 33;
		t.y = 220;
		return t;
	};
	_proto.PeopleChat_text_i = function () {
		var t = new eui.Label();
		this.PeopleChat_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 16;
		t.size = 15;
		t.text = "Label";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 38;
		t.x = 49;
		t.y = 553;
		return t;
	};
	_proto.PeopleChat_i = function () {
		var t = new eui.Button();
		this.PeopleChat = t;
		t.height = 25;
		t.label = "";
		t.width = 45;
		t.x = 45;
		t.y = 547.92;
		t.skinName = TheFarmLobbySkin$Skin94;
		return t;
	};
	_proto.ChatTools_i = function () {
		var t = new eui.Scroller();
		this.ChatTools = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 298.76;
		t.width = 298.3;
		t.x = 39;
		t.y = 230;
		t.viewport = this.ChatToolsw_i();
		return t;
	};
	_proto.ChatToolsw_i = function () {
		var t = new eui.Group();
		this.ChatToolsw = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 544.97;
		t.width = 393.3;
		return t;
	};
	_proto._Image164_i = function () {
		var t = new eui.Image();
		t.height = 25;
		t.source = "BKjuxing@3x_png";
		t.width = 170;
		t.x = 94;
		t.y = 549.92;
		return t;
	};
	_proto.ChatFrame_i = function () {
		var t = new eui.TextInput();
		this.ChatFrame = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.width = 170;
		t.x = 94;
		t.y = 548.92;
		return t;
	};
	_proto.ChatIcon_i = function () {
		var t = new eui.Button();
		this.ChatIcon = t;
		t.anchorOffsetX = 0;
		t.height = 22;
		t.label = "";
		t.width = 22;
		t.x = 271;
		t.y = 549.92;
		t.skinName = TheFarmLobbySkin$Skin95;
		return t;
	};
	_proto.Send_i = function () {
		var t = new eui.Button();
		this.Send = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 20;
		t.label = "";
		t.width = 35;
		t.x = 294;
		t.y = 549.92;
		t.skinName = TheFarmLobbySkin$Skin96;
		return t;
	};
	_proto.Expression_bar_i = function () {
		var t = new eui.Group();
		this.Expression_bar = t;
		t.height = 60;
		t.visible = false;
		t.width = 300;
		t.x = 38;
		t.y = 479;
		t.elementsContent = [this._Image165_i(),this._Image166_i(),this._Image167_i(),this._Image168_i(),this._Image169_i(),this._Image170_i(),this._Image171_i(),this._Image172_i(),this._Image173_i(),this._Image174_i(),this._Image175_i(),this._Image176_i(),this._Image177_i(),this._Image178_i(),this._Image179_i(),this._Image180_i(),this._Image181_i(),this._Image182_i(),this._Image183_i(),this._Image184_i(),this._Image185_i(),this._Image186_i(),this._Image187_i(),this._Image188_i(),this._Image189_i(),this._Image190_i(),this._Image191_i(),this._Image192_i(),this._Image193_i(),this._Image194_i(),this._Image195_i(),this._Image196_i(),this._Image197_i(),this._Image198_i(),this._Image199_i(),this._Image200_i()];
		return t;
	};
	_proto._Image165_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny01]";
		t.source = "[funny01]_gif";
		t.width = 20;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image166_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny02]";
		t.source = "[funny02]_gif";
		t.width = 20;
		t.x = 20;
		t.y = 0;
		return t;
	};
	_proto._Image167_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny03]";
		t.source = "[funny03]_gif";
		t.width = 20;
		t.x = 40;
		t.y = 0;
		return t;
	};
	_proto._Image168_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny04]";
		t.source = "[funny04]_gif";
		t.width = 20;
		t.x = 60;
		t.y = 0;
		return t;
	};
	_proto._Image169_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny05]";
		t.source = "[funny05]_gif";
		t.width = 20;
		t.x = 80;
		t.y = 0;
		return t;
	};
	_proto._Image170_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny06]";
		t.source = "[funny06]_gif";
		t.width = 20;
		t.x = 100;
		t.y = 0;
		return t;
	};
	_proto._Image171_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny07]";
		t.source = "[funny07]_gif";
		t.width = 20;
		t.x = 120;
		t.y = 0;
		return t;
	};
	_proto._Image172_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny08]";
		t.source = "[funny08]_gif";
		t.width = 20;
		t.x = 140;
		t.y = 0;
		return t;
	};
	_proto._Image173_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny09]";
		t.source = "[funny09]_gif";
		t.width = 20;
		t.x = 160;
		t.y = 0;
		return t;
	};
	_proto._Image174_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny10]";
		t.source = "[funny10]_gif";
		t.width = 20;
		t.x = 180;
		t.y = 0;
		return t;
	};
	_proto._Image175_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny11]";
		t.source = "[funny11]_gif";
		t.width = 20;
		t.x = 200;
		t.y = 0;
		return t;
	};
	_proto._Image176_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny12]";
		t.source = "[funny12]_gif";
		t.width = 20;
		t.x = 220;
		t.y = 0;
		return t;
	};
	_proto._Image177_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny13]";
		t.source = "[funny13]_gif";
		t.width = 20;
		t.x = 240;
		t.y = 0;
		return t;
	};
	_proto._Image178_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny14]";
		t.source = "[funny14]_gif";
		t.width = 20;
		t.x = 260;
		t.y = 0;
		return t;
	};
	_proto._Image179_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny15]";
		t.source = "[funny15]_gif";
		t.width = 20;
		t.x = 280;
		t.y = 0;
		return t;
	};
	_proto._Image180_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny16]";
		t.source = "[funny16]_gif";
		t.width = 20;
		t.x = 0;
		t.y = 20;
		return t;
	};
	_proto._Image181_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny17]";
		t.source = "[funny17]_gif";
		t.width = 20;
		t.x = 20;
		t.y = 20;
		return t;
	};
	_proto._Image182_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny18]";
		t.source = "[funny18]_gif";
		t.width = 20;
		t.x = 40;
		t.y = 20;
		return t;
	};
	_proto._Image183_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny19]";
		t.source = "[funny19]_gif";
		t.width = 20;
		t.x = 60;
		t.y = 20;
		return t;
	};
	_proto._Image184_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny20]";
		t.source = "[funny20]_gif";
		t.width = 20;
		t.x = 80;
		t.y = 20;
		return t;
	};
	_proto._Image185_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny21]";
		t.source = "[funny21]_gif";
		t.width = 20;
		t.x = 100;
		t.y = 20;
		return t;
	};
	_proto._Image186_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny22]";
		t.source = "[funny22]_gif";
		t.width = 20;
		t.x = 120;
		t.y = 20;
		return t;
	};
	_proto._Image187_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny23]";
		t.source = "[funny23]_gif";
		t.width = 20;
		t.x = 140;
		t.y = 20;
		return t;
	};
	_proto._Image188_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny24]";
		t.source = "[funny24]_gif";
		t.width = 20;
		t.x = 160;
		t.y = 20;
		return t;
	};
	_proto._Image189_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny25]";
		t.source = "[funny25]_gif";
		t.width = 20;
		t.x = 180;
		t.y = 20;
		return t;
	};
	_proto._Image190_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny26]";
		t.source = "[funny26]_gif";
		t.width = 20;
		t.x = 200;
		t.y = 20;
		return t;
	};
	_proto._Image191_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny27]";
		t.source = "[funny27]_gif";
		t.width = 20;
		t.x = 220;
		t.y = 20;
		return t;
	};
	_proto._Image192_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny28]";
		t.source = "[funny28]_gif";
		t.width = 20;
		t.x = 240;
		t.y = 20;
		return t;
	};
	_proto._Image193_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny29]";
		t.source = "[funny29]_gif";
		t.width = 20;
		t.x = 260;
		t.y = 20;
		return t;
	};
	_proto._Image194_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny30]";
		t.source = "[funny30]_gif";
		t.width = 20;
		t.x = 280;
		t.y = 20;
		return t;
	};
	_proto._Image195_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny31]";
		t.source = "[funny31]_gif";
		t.width = 20;
		t.x = 0;
		t.y = 40;
		return t;
	};
	_proto._Image196_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny32]";
		t.source = "[funny32]_gif";
		t.width = 20;
		t.x = 20;
		t.y = 40;
		return t;
	};
	_proto._Image197_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny33]";
		t.source = "[funny33]_gif";
		t.width = 20;
		t.x = 40;
		t.y = 40;
		return t;
	};
	_proto._Image198_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny34]";
		t.source = "[funny34]_gif";
		t.width = 20;
		t.x = 60;
		t.y = 40;
		return t;
	};
	_proto._Image199_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny35]";
		t.source = "[funny35]_gif";
		t.width = 20;
		t.x = 80;
		t.y = 40;
		return t;
	};
	_proto._Image200_i = function () {
		var t = new eui.Image();
		t.height = 20;
		t.name = "[funny36]";
		t.source = "[funny36]_gif";
		t.width = 20;
		t.x = 100;
		t.y = 40;
		return t;
	};
	_proto.SQBJ_i = function () {
		var t = new eui.Image();
		this.SQBJ = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 105;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "BKjuxing@3x_png";
		t.visible = false;
		t.width = 60;
		t.x = 38;
		t.y = 444;
		return t;
	};
	_proto.FriendsBar0_i = function () {
		var t = new eui.Scroller();
		this.FriendsBar0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 104;
		t.name = "1";
		t.visible = false;
		t.width = 60;
		t.x = 39;
		t.y = 446;
		t.viewport = this.FriendsBar1_i();
		return t;
	};
	_proto.FriendsBar1_i = function () {
		var t = new eui.Group();
		this.FriendsBar1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 81;
		t.width = 75;
		t.x = 0;
		t.y = -1;
		return t;
	};
	_proto._Scroller6_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 340;
		t.width = 305;
		t.x = 35;
		t.y = 216;
		t.viewport = this.ApplyFor_i();
		return t;
	};
	_proto.ApplyFor_i = function () {
		var t = new eui.Group();
		this.ApplyFor = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 692.43;
		t.width = 396.3;
		t.x = -1.52;
		t.y = -3.03;
		return t;
	};
	_proto.FindMyPhone_i = function () {
		var t = new eui.Group();
		this.FindMyPhone = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image201_i(),this._Image202_i(),this._Image203_i(),this.FindMyPhone_close_i(),this.PhoneNumber_i(),this.Find_i(),this._Scroller7_i(),this._Image204_i(),this._Image205_i(),this._Image206_i(),this._Image207_i()];
		return t;
	};
	_proto._Image201_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.visible = false;
		t.width = 500;
		t.x = -38;
		t.y = -134;
		return t;
	};
	_proto._Image202_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 563;
		t.source = "beijing123@3x_png";
		t.width = 352;
		t.x = 15;
		t.y = 47.33;
		return t;
	};
	_proto._Image203_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 29;
		t.source = "juxingbs@3x_png";
		t.width = 165;
		t.x = 158;
		t.y = 145;
		return t;
	};
	_proto.FindMyPhone_close_i = function () {
		var t = new eui.Button();
		this.FindMyPhone_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 54;
		t.skinName = TheFarmLobbySkin$Skin97;
		return t;
	};
	_proto.PhoneNumber_i = function () {
		var t = new eui.TextInput();
		this.PhoneNumber = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 29;
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 165;
		t.x = 158;
		t.y = 145;
		return t;
	};
	_proto.Find_i = function () {
		var t = new eui.Button();
		this.Find = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 86;
		t.x = 145;
		t.y = 195;
		t.skinName = TheFarmLobbySkin$Skin98;
		return t;
	};
	_proto._Scroller7_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 270;
		t.width = 315;
		t.x = 30;
		t.y = 290;
		t.viewport = this.PlayerList_i();
		return t;
	};
	_proto.PlayerList_i = function () {
		var t = new eui.Group();
		this.PlayerList = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 365.15;
		t.width = 389.06;
		t.x = -2;
		return t;
	};
	_proto._Image204_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 19;
		t.source = "sousuo@3x_png";
		t.width = 98;
		t.x = 50;
		t.y = 150;
		return t;
	};
	_proto._Image205_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 26;
		t.source = "fujinwanjia@3x_png";
		t.width = 75;
		t.x = 47;
		t.y = 260;
		return t;
	};
	_proto._Image206_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "tianjiahaoyou@3x_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto._Image207_i = function () {
		var t = new eui.Image();
		t.height = 1;
		t.source = "fenge@2x_png";
		t.width = 320;
		t.x = 25.37;
		t.y = 244;
		return t;
	};
	_proto.FriendSet_i = function () {
		var t = new eui.Group();
		this.FriendSet = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image208_i(),this._Image209_i(),this.FriendSet_close_i(),this.MobileSearch_i(),this.RecommendationFriend_i(),this._Image210_i(),this._Image211_i(),this._Image212_i()];
		return t;
	};
	_proto._Image208_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -62;
		t.y = -239.36;
		return t;
	};
	_proto._Image209_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 221;
		t.source = "set2_png";
		t.width = 328;
		t.x = 24;
		t.y = 230;
		return t;
	};
	_proto.FriendSet_close_i = function () {
		var t = new eui.Button();
		this.FriendSet_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 300;
		t.y = 233;
		t.skinName = TheFarmLobbySkin$Skin99;
		return t;
	};
	_proto.MobileSearch_i = function () {
		var t = new eui.ToggleSwitch();
		this.MobileSearch = t;
		t.height = 31;
		t.label = "ToggleButton";
		t.width = 68;
		t.x = 233;
		t.y = 296;
		return t;
	};
	_proto.RecommendationFriend_i = function () {
		var t = new eui.ToggleSwitch();
		this.RecommendationFriend = t;
		t.height = 31;
		t.label = "ToggleButton";
		t.width = 68;
		t.x = 233;
		t.y = 351;
		return t;
	};
	_proto._Image210_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 23;
		t.source = "shoujihao@3x_png";
		t.width = 140;
		t.x = 76;
		t.y = 301;
		return t;
	};
	_proto._Image211_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 23;
		t.source = "fujinren@3x_png";
		t.width = 123;
		t.x = 75;
		t.y = 356;
		return t;
	};
	_proto._Image212_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.source = "shezhi@3x(1)_png";
		t.width = 147;
		t.x = 116;
		t.y = 223;
		return t;
	};
	_proto.TASK_i = function () {
		var t = new eui.Group();
		this.TASK = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image213_i(),this._Image214_i(),this._Image215_i(),this._Image216_i(),this._Image217_i(),this._Image218_i(),this.TASK_close_i(),this._Image219_i(),this._Image220_i(),this._Image221_i(),this._Image222_i(),this._Label30_i(),this._Label31_i(),this._Label32_i(),this._Label33_i(),this._Image223_i(),this.tupian0_i(),this.tupian1_i(),this.tupian2_i(),this.tupian3_i(),this.tupian4_i(),this.tupian5_i(),this.Task1_i(),this.HebdomadTask1_i(),this.QDRW_i(),this.HYRW_i(),this.WBRW_i(),this.STRW_i(),this._Label34_i(),this._Label35_i(),this._Label36_i(),this.HLBZJ1_i(),this.HLBZJ0_i(),this.HLBZJ2_i(),this.jieshaojiemian_i()];
		return t;
	};
	_proto._Image213_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image214_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 500;
		t.source = "bj2_png";
		t.width = 341;
		t.x = 17;
		t.y = 41;
		return t;
	};
	_proto._Image215_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 66;
		t.source = "rectangle12_png";
		t.width = 310;
		t.x = 30;
		t.y = 258;
		return t;
	};
	_proto._Image216_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 66;
		t.source = "rectangle12_png";
		t.width = 310;
		t.x = 30;
		t.y = 338;
		return t;
	};
	_proto._Image217_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 66;
		t.source = "rectangle12_png";
		t.width = 310;
		t.x = 30;
		t.y = 418;
		return t;
	};
	_proto._Image218_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 66;
		t.source = "rectangle12_png";
		t.width = 310;
		t.x = 30;
		t.y = 180;
		return t;
	};
	_proto.TASK_close_i = function () {
		var t = new eui.Button();
		this.TASK_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 314;
		t.y = 60;
		t.skinName = TheFarmLobbySkin$Skin100;
		return t;
	};
	_proto._Image219_i = function () {
		var t = new eui.Image();
		t.height = 54;
		t.source = "qiandao@3x_png";
		t.width = 60;
		t.x = 31;
		t.y = 185;
		return t;
	};
	_proto._Image220_i = function () {
		var t = new eui.Image();
		t.height = 54;
		t.source = "haoyou@3x_png";
		t.width = 60;
		t.x = 31;
		t.y = 265;
		return t;
	};
	_proto._Image221_i = function () {
		var t = new eui.Image();
		t.height = 54;
		t.source = "wabao@3x_png";
		t.width = 60;
		t.x = 31;
		t.y = 345;
		return t;
	};
	_proto._Image222_i = function () {
		var t = new eui.Image();
		t.height = 54;
		t.source = "shaitu1@3x_png";
		t.width = 60;
		t.x = 31;
		t.y = 425;
		return t;
	};
	_proto._Label30_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 13;
		t.size = 13;
		t.text = "签到任务：每日签到可领取以下奖励";
		t.textColor = 0xff860f;
		t.width = 340;
		t.x = 93;
		t.y = 188;
		return t;
	};
	_proto._Label31_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 13;
		t.size = 13;
		t.text = "好友任务：好友达到10人可领取以下奖励";
		t.textColor = 0xff860f;
		t.width = 340;
		t.x = 93;
		t.y = 268;
		return t;
	};
	_proto._Label32_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 13;
		t.size = 13;
		t.text = "挖宝任务：每日挖宝一次可领取以下奖励";
		t.textColor = 0xff860f;
		t.width = 340;
		t.x = 93;
		t.y = 352;
		return t;
	};
	_proto._Label33_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 13;
		t.size = 13;
		t.text = "晒图任务：葫芦币兑换成功并晒图分享";
		t.textColor = 0xff860f;
		t.width = 340;
		t.x = 93;
		t.y = 432;
		return t;
	};
	_proto._Image223_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "renwu_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto.tupian0_i = function () {
		var t = new eui.Image();
		this.tupian0 = t;
		t.height = 30;
		t.name = "hulu";
		t.source = "HLZZDJ_png";
		t.width = 34;
		t.x = 93;
		t.y = 206;
		return t;
	};
	_proto.tupian1_i = function () {
		var t = new eui.Image();
		this.tupian1 = t;
		t.height = 30;
		t.name = "dagoubang";
		t.source = "goubang@3x_daoju_png";
		t.width = 34;
		t.x = 93;
		t.y = 286;
		return t;
	};
	_proto.tupian2_i = function () {
		var t = new eui.Image();
		this.tupian2 = t;
		t.height = 30;
		t.name = "hulu";
		t.source = "HLZZDJ_png";
		t.width = 34;
		t.x = 137;
		t.y = 286;
		return t;
	};
	_proto.tupian3_i = function () {
		var t = new eui.Image();
		this.tupian3 = t;
		t.height = 30;
		t.name = "hulu";
		t.source = "HLZZDJ_png";
		t.width = 34;
		t.x = 93;
		t.y = 370;
		return t;
	};
	_proto.tupian4_i = function () {
		var t = new eui.Image();
		this.tupian4 = t;
		t.height = 30;
		t.name = "feiliao";
		t.source = "shifei_png";
		t.width = 34;
		t.x = 93;
		t.y = 450;
		return t;
	};
	_proto.tupian5_i = function () {
		var t = new eui.Image();
		this.tupian5 = t;
		t.height = 30;
		t.name = "chujigouliang";
		t.source = "chujigouliang@3x_daoju_png";
		t.width = 34;
		t.x = 137;
		t.y = 450;
		return t;
	};
	_proto.Task1_i = function () {
		var t = new eui.Image();
		this.Task1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "changuiCGRWK@3x_png";
		t.width = 111;
		t.x = 82;
		t.y = 129;
		return t;
	};
	_proto.HebdomadTask1_i = function () {
		var t = new eui.Image();
		this.HebdomadTask1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "xianshiXSRWY@3x_png";
		t.width = 111;
		t.x = 183;
		t.y = 129;
		return t;
	};
	_proto.QDRW_i = function () {
		var t = new eui.Image();
		this.QDRW = t;
		t.height = 25;
		t.name = "0";
		t.source = "qiandaolingqu@3x_png";
		t.width = 85;
		t.x = 248;
		t.y = 214;
		return t;
	};
	_proto.HYRW_i = function () {
		var t = new eui.Image();
		this.HYRW = t;
		t.height = 25;
		t.name = "0";
		t.source = "daiwancheng@3x_png";
		t.width = 64;
		t.x = 269;
		t.y = 294;
		return t;
	};
	_proto.WBRW_i = function () {
		var t = new eui.Image();
		this.WBRW = t;
		t.height = 25;
		t.name = "0";
		t.source = "daiwancheng@3x_png";
		t.width = 64;
		t.x = 269;
		t.y = 374;
		return t;
	};
	_proto.STRW_i = function () {
		var t = new eui.Image();
		this.STRW = t;
		t.height = 25;
		t.name = "0";
		t.source = "daiwancheng@3x_png";
		t.width = 64;
		t.x = 269;
		t.y = 454;
		return t;
	};
	_proto._Label34_i = function () {
		var t = new eui.Label();
		t.size = 10;
		t.text = "X2";
		t.textColor = 0xf70000;
		t.x = 116;
		t.y = 224;
		return t;
	};
	_proto._Label35_i = function () {
		var t = new eui.Label();
		t.size = 10;
		t.text = "X5";
		t.textColor = 0xF70000;
		t.x = 160;
		t.y = 304;
		return t;
	};
	_proto._Label36_i = function () {
		var t = new eui.Label();
		t.size = 10;
		t.text = "X3";
		t.textColor = 0xF70000;
		t.x = 116;
		t.y = 388;
		return t;
	};
	_proto.HLBZJ1_i = function () {
		var t = new eui.Label();
		this.HLBZJ1 = t;
		t.size = 10;
		t.text = "+2";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 301;
		t.y = 225;
		return t;
	};
	_proto.HLBZJ0_i = function () {
		var t = new eui.Label();
		this.HLBZJ0 = t;
		t.size = 10;
		t.text = "+5";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 301;
		t.y = 304;
		return t;
	};
	_proto.HLBZJ2_i = function () {
		var t = new eui.Label();
		this.HLBZJ2 = t;
		t.size = 10;
		t.text = "+3";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 301;
		t.y = 383;
		return t;
	};
	_proto.jieshaojiemian_i = function () {
		var t = new eui.Group();
		this.jieshaojiemian = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.touchThrough = true;
		t.elementsContent = [this.DJJJMP_i(),this.DJJJMP_neirou_i()];
		return t;
	};
	_proto.DJJJMP_i = function () {
		var t = new eui.Image();
		this.DJJJMP = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 37;
		t.source = "DJJJMP_png";
		t.visible = false;
		t.width = 126;
		t.x = 65;
		t.y = 174;
		return t;
	};
	_proto.DJJJMP_neirou_i = function () {
		var t = new eui.Label();
		this.DJJJMP_neirou = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.size = 15;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 59;
		t.y = 182;
		return t;
	};
	_proto.DeadlineTASK_i = function () {
		var t = new eui.Group();
		this.DeadlineTASK = t;
		t.height = 667;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image224_i(),this._Image225_i(),this._Image226_i(),this._Image227_i(),this.DeadlineTASK_close_i(),this.Task2_i(),this.HebdomadTask2_i(),this._Image228_i(),this._Image229_i(),this._Image230_i(),this.day_i(),this.day0_i(),this.day1_i(),this.day2_i(),this.day3_i(),this.day4_i(),this.day5_i(),this.jieshaojiemian0_i(),this.OneDays_i(),this.TowDays_i(),this.ThreeDays_i(),this.FourDays_i(),this.FiveDays_i(),this.SixDays_i(),this.SevenDays_i()];
		return t;
	};
	_proto._Image224_i = function () {
		var t = new eui.Image();
		t.height = 667;
		t.source = "TouMingBeiJing_png";
		t.width = 375;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image225_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 567;
		t.source = "BJZ3@3x_png";
		t.width = 341;
		t.x = 17;
		t.y = 41;
		return t;
	};
	_proto._Image226_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "renwu_png";
		t.width = 168;
		t.x = 105;
		t.y = 40;
		return t;
	};
	_proto._Image227_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 65;
		t.source = "Seven_days_carnival_png";
		t.width = 197;
		t.x = 91;
		t.y = 185;
		return t;
	};
	_proto.DeadlineTASK_close_i = function () {
		var t = new eui.Button();
		this.DeadlineTASK_close = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 316;
		t.y = 62;
		t.skinName = TheFarmLobbySkin$Skin101;
		return t;
	};
	_proto.Task2_i = function () {
		var t = new eui.Image();
		this.Task2 = t;
		t.height = 40;
		t.source = "changguiCGRWY@3x_png";
		t.width = 111;
		t.x = 82;
		t.y = 129;
		return t;
	};
	_proto.HebdomadTask2_i = function () {
		var t = new eui.Image();
		this.HebdomadTask2 = t;
		t.height = 40;
		t.source = "xianshiXSRWK@3x_png";
		t.width = 111;
		t.x = 185;
		t.y = 129;
		return t;
	};
	_proto._Image228_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 193;
		t.source = "juxingXSRWBJ@3x_png";
		t.width = 300;
		t.x = 39;
		t.y = 245;
		return t;
	};
	_proto._Image229_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 121;
		t.source = "jx_png";
		t.width = 298;
		t.x = 39;
		t.y = 446;
		return t;
	};
	_proto._Image230_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 30;
		t.source = "Reward_items_png";
		t.width = 80;
		t.x = 47;
		t.y = 450;
		return t;
	};
	_proto.day_i = function () {
		var t = new eui.Group();
		this.day = t;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image231_i(),this._Label37_i(),this._Label38_i(),this._Label39_i(),this._Image232_i(),this.tp1_i(),this.DJLQ_i(),this.QDYC_i(),this._Label40_i(),this.ZZYCZW_i(),this._Label41_i()];
		return t;
	};
	_proto._Image231_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 300;
		return t;
	};
	_proto._Label37_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.fontFamily = "Arial";
		t.height = 18;
		t.size = 14;
		t.text = "签到一次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 305;
		return t;
	};
	_proto._Label38_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.size = 14;
		t.text = "种植一次植物";
		t.textColor = 0x000000;
		t.x = 89;
		t.y = 365;
		return t;
	};
	_proto._Label39_i = function () {
		var t = new eui.Label();
		t.size = 13;
		t.text = "X10";
		t.textColor = 0x000000;
		t.x = 80;
		t.y = 512;
		return t;
	};
	_proto._Image232_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 360;
		return t;
	};
	_proto.tp1_i = function () {
		var t = new eui.Image();
		this.tp1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "HLZZDJ_png";
		t.width = 45;
		t.x = 49;
		t.y = 489;
		return t;
	};
	_proto.DJLQ_i = function () {
		var t = new eui.Button();
		this.DJLQ = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin102;
		return t;
	};
	_proto.QDYC_i = function () {
		var t = new eui.Label();
		this.QDYC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 303;
		return t;
	};
	_proto._Label40_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 303;
		return t;
	};
	_proto.ZZYCZW_i = function () {
		var t = new eui.Label();
		this.ZZYCZW = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 363;
		return t;
	};
	_proto._Label41_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 363;
		return t;
	};
	_proto.day0_i = function () {
		var t = new eui.Group();
		this.day0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.DrillOne0_i(),this.TaskOne0_i(),this.TaskThree0_i(),this.DrillThree0_i(),this.tp2_i(),this.SQYCZW_i(),this._Label42_i(),this.JCCGYC_i(),this._Label43_i(),this.DJLQ0_i(),this._Label44_i()];
		return t;
	};
	_proto.DrillOne0_i = function () {
		var t = new eui.Image();
		this.DrillOne0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 300;
		return t;
	};
	_proto.TaskOne0_i = function () {
		var t = new eui.Label();
		this.TaskOne0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "收取一次植物";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 305;
		return t;
	};
	_proto.TaskThree0_i = function () {
		var t = new eui.Label();
		this.TaskThree0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "浇水、除虫、除草共一次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 366;
		return t;
	};
	_proto.DrillThree0_i = function () {
		var t = new eui.Image();
		this.DrillThree0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 360;
		return t;
	};
	_proto.tp2_i = function () {
		var t = new eui.Image();
		this.tp2 = t;
		t.height = 40;
		t.source = "manure@3x_png";
		t.width = 45;
		t.x = 49;
		t.y = 489;
		return t;
	};
	_proto.SQYCZW_i = function () {
		var t = new eui.Label();
		this.SQYCZW = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 303;
		return t;
	};
	_proto._Label42_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 303;
		return t;
	};
	_proto.JCCGYC_i = function () {
		var t = new eui.Label();
		this.JCCGYC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 363;
		return t;
	};
	_proto._Label43_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 363;
		return t;
	};
	_proto.DJLQ0_i = function () {
		var t = new eui.Button();
		this.DJLQ0 = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin103;
		return t;
	};
	_proto._Label44_i = function () {
		var t = new eui.Label();
		t.size = 13;
		t.text = "X 1";
		t.textColor = 0x000000;
		t.x = 83;
		t.y = 509;
		return t;
	};
	_proto.day1_i = function () {
		var t = new eui.Group();
		this.day1 = t;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.TaskThree1_i(),this.TaskThree2_i(),this.TaskThree3_i(),this.DJSZPM_i(),this._Label45_i(),this.GMYKZZ_i(),this._Label46_i(),this.JCCGSC_i(),this._Label47_i(),this.DrillThree1_i(),this.DrillThree2_i(),this.DrillThree3_i(),this.tp4_i(),this.tp3_i(),this.DJLQ1_i()];
		return t;
	};
	_proto.TaskThree1_i = function () {
		var t = new eui.Label();
		this.TaskThree1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.size = 14;
		t.text = "将等级提升至贫民";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 305;
		return t;
	};
	_proto.TaskThree2_i = function () {
		var t = new eui.Label();
		this.TaskThree2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.size = 14;
		t.text = "购买一颗种子";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 335;
		return t;
	};
	_proto.TaskThree3_i = function () {
		var t = new eui.Label();
		this.TaskThree3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.size = 14;
		t.text = "浇水、除虫、除草共三次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 365;
		return t;
	};
	_proto.DJSZPM_i = function () {
		var t = new eui.Label();
		this.DJSZPM = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 303;
		return t;
	};
	_proto._Label45_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 303;
		return t;
	};
	_proto.GMYKZZ_i = function () {
		var t = new eui.Label();
		this.GMYKZZ = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 333;
		return t;
	};
	_proto._Label46_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 333;
		return t;
	};
	_proto.JCCGSC_i = function () {
		var t = new eui.Label();
		this.JCCGSC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 363;
		return t;
	};
	_proto._Label47_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/3";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 363;
		return t;
	};
	_proto.DrillThree1_i = function () {
		var t = new eui.Image();
		this.DrillThree1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 300;
		return t;
	};
	_proto.DrillThree2_i = function () {
		var t = new eui.Image();
		this.DrillThree2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 330;
		return t;
	};
	_proto.DrillThree3_i = function () {
		var t = new eui.Image();
		this.DrillThree3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "three@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 360;
		return t;
	};
	_proto.tp4_i = function () {
		var t = new eui.Image();
		this.tp4 = t;
		t.height = 40;
		t.source = "HLZZDJ_png";
		t.width = 45;
		t.x = 104;
		t.y = 489;
		return t;
	};
	_proto.tp3_i = function () {
		var t = new eui.Image();
		this.tp3 = t;
		t.height = 40;
		t.source = "baijinchutou@3x_daoju_png";
		t.width = 45;
		t.x = 49;
		t.y = 489;
		return t;
	};
	_proto.DJLQ1_i = function () {
		var t = new eui.Button();
		this.DJLQ1 = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin104;
		return t;
	};
	_proto.day2_i = function () {
		var t = new eui.Group();
		this.day2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.leij_i(),this.TaskThree6_i(),this.DrillTow2_i(),this.DrillTow0_i(),this.tp6_i(),this.LJWBSC_i(),this._Label48_i(),this.LJSQHLSC_i(),this._Label49_i(),this.tp5_i(),this.DJLQ2_i()];
		return t;
	};
	_proto.leij_i = function () {
		var t = new eui.Label();
		this.leij = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "累计挖宝三次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 305;
		return t;
	};
	_proto.TaskThree6_i = function () {
		var t = new eui.Label();
		this.TaskThree6 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.size = 14;
		t.text = "累计收取葫芦三次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 365;
		return t;
	};
	_proto.DrillTow2_i = function () {
		var t = new eui.Image();
		this.DrillTow2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 300;
		return t;
	};
	_proto.DrillTow0_i = function () {
		var t = new eui.Image();
		this.DrillTow0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 360;
		return t;
	};
	_proto.tp6_i = function () {
		var t = new eui.Image();
		this.tp6 = t;
		t.height = 40;
		t.source = "chujigouliang@3x_daoju_png";
		t.width = 45;
		t.x = 104;
		t.y = 489;
		return t;
	};
	_proto.LJWBSC_i = function () {
		var t = new eui.Label();
		this.LJWBSC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 303;
		return t;
	};
	_proto._Label48_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/3";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 303;
		return t;
	};
	_proto.LJSQHLSC_i = function () {
		var t = new eui.Label();
		this.LJSQHLSC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 363;
		return t;
	};
	_proto._Label49_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/3";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 363;
		return t;
	};
	_proto.tp5_i = function () {
		var t = new eui.Image();
		this.tp5 = t;
		t.height = 40;
		t.source = "sanbei@3x_daoju_png";
		t.width = 45;
		t.x = 49;
		t.y = 489;
		return t;
	};
	_proto.DJLQ2_i = function () {
		var t = new eui.Button();
		this.DJLQ2 = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin105;
		return t;
	};
	_proto.day3_i = function () {
		var t = new eui.Group();
		this.day3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.jiao_i(),this.jiao0_i(),this.jiao1_i(),this.TJSGHY_i(),this._Label50_i(),this.WYLCG_i(),this._Label51_i(),this.SYFLLC_i(),this._Label52_i(),this.DrillTow3_i(),this.DrillTow1_i(),this.DrillTow4_i(),this.tp8_i(),this.tp0_i(),this.tp7_i(),this.DJLQ3_i()];
		return t;
	};
	_proto.jiao_i = function () {
		var t = new eui.Label();
		this.jiao = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "喂养两次狗";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 305;
		return t;
	};
	_proto.jiao0_i = function () {
		var t = new eui.Label();
		this.jiao0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "使用肥料两次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 335;
		return t;
	};
	_proto.jiao1_i = function () {
		var t = new eui.Label();
		this.jiao1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "添加10个好友";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 365;
		return t;
	};
	_proto.TJSGHY_i = function () {
		var t = new eui.Label();
		this.TJSGHY = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 363;
		return t;
	};
	_proto._Label50_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/10";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 363;
		return t;
	};
	_proto.WYLCG_i = function () {
		var t = new eui.Label();
		this.WYLCG = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 303;
		return t;
	};
	_proto._Label51_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/2";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 303;
		return t;
	};
	_proto.SYFLLC_i = function () {
		var t = new eui.Label();
		this.SYFLLC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 333;
		return t;
	};
	_proto._Label52_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/2";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 333;
		return t;
	};
	_proto.DrillTow3_i = function () {
		var t = new eui.Image();
		this.DrillTow3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 300;
		return t;
	};
	_proto.DrillTow1_i = function () {
		var t = new eui.Image();
		this.DrillTow1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 330;
		return t;
	};
	_proto.DrillTow4_i = function () {
		var t = new eui.Image();
		this.DrillTow4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "three@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 360;
		return t;
	};
	_proto.tp8_i = function () {
		var t = new eui.Image();
		this.tp8 = t;
		t.height = 40;
		t.source = "mofachuji@3x_daoju_png";
		t.width = 45;
		t.x = 104;
		t.y = 489;
		return t;
	};
	_proto.tp0_i = function () {
		var t = new eui.Image();
		this.tp0 = t;
		t.height = 40;
		t.source = "sanbei@3x_daoju_png";
		t.width = 45;
		t.x = 159;
		t.y = 489;
		return t;
	};
	_proto.tp7_i = function () {
		var t = new eui.Image();
		this.tp7 = t;
		t.height = 40;
		t.source = "goubang@3x_daoju_png";
		t.width = 45;
		t.x = 49;
		t.y = 489;
		return t;
	};
	_proto.DJLQ3_i = function () {
		var t = new eui.Button();
		this.DJLQ3 = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin106;
		return t;
	};
	_proto.day4_i = function () {
		var t = new eui.Group();
		this.day4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.visible = false;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.DrillOne4_i(),this.DJSZZN_i(),this._Label53_i(),this.SQSCHYDHL_i(),this._Label54_i(),this.WBWC_i(),this._Label55_i(),this.SQWCHL_i(),this._Label56_i(),this.DrillOne1_i(),this.DrillOne2_i(),this.TaskOne4_i(),this.TaskOne1_i(),this.TaskOne2_i(),this.TaskThree4_i(),this.DrillThree4_i(),this.tp11_i(),this.tp10_i(),this.tp9_i(),this.DJLQ4_i()];
		return t;
	};
	_proto.DrillOne4_i = function () {
		var t = new eui.Image();
		this.DrillOne4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 300;
		return t;
	};
	_proto.DJSZZN_i = function () {
		var t = new eui.Label();
		this.DJSZZN = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 303;
		return t;
	};
	_proto._Label53_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 303;
		return t;
	};
	_proto.SQSCHYDHL_i = function () {
		var t = new eui.Label();
		this.SQSCHYDHL = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 333;
		return t;
	};
	_proto._Label54_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/3";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 333;
		return t;
	};
	_proto.WBWC_i = function () {
		var t = new eui.Label();
		this.WBWC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 363;
		return t;
	};
	_proto._Label55_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/5";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 363;
		return t;
	};
	_proto.SQWCHL_i = function () {
		var t = new eui.Label();
		this.SQWCHL = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 393;
		return t;
	};
	_proto._Label56_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/5";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 393;
		return t;
	};
	_proto.DrillOne1_i = function () {
		var t = new eui.Image();
		this.DrillOne1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 330;
		return t;
	};
	_proto.DrillOne2_i = function () {
		var t = new eui.Image();
		this.DrillOne2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "four@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 390;
		return t;
	};
	_proto.TaskOne4_i = function () {
		var t = new eui.Label();
		this.TaskOne4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "等级升至中农";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 305;
		return t;
	};
	_proto.TaskOne1_i = function () {
		var t = new eui.Label();
		this.TaskOne1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "收取三次好友的葫芦";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 335;
		return t;
	};
	_proto.TaskOne2_i = function () {
		var t = new eui.Label();
		this.TaskOne2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "收取五次葫芦";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 395;
		return t;
	};
	_proto.TaskThree4_i = function () {
		var t = new eui.Label();
		this.TaskThree4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "挖宝五次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 366;
		return t;
	};
	_proto.DrillThree4_i = function () {
		var t = new eui.Image();
		this.DrillThree4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "three@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 360;
		return t;
	};
	_proto.tp11_i = function () {
		var t = new eui.Image();
		this.tp11 = t;
		t.height = 40;
		t.source = "sanbei@3x_daoju_png";
		t.width = 45;
		t.x = 159;
		t.y = 489;
		return t;
	};
	_proto.tp10_i = function () {
		var t = new eui.Image();
		this.tp10 = t;
		t.height = 40;
		t.source = "zuanshichutou@3x_daoju_png";
		t.width = 45;
		t.x = 104;
		t.y = 489;
		return t;
	};
	_proto.tp9_i = function () {
		var t = new eui.Image();
		this.tp9 = t;
		t.height = 40;
		t.source = "zhongjigouliang@3x_daoju_png";
		t.width = 45;
		t.x = 49;
		t.y = 489;
		return t;
	};
	_proto.DJLQ4_i = function () {
		var t = new eui.Button();
		this.DJLQ4 = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin107;
		return t;
	};
	_proto.day5_i = function () {
		var t = new eui.Group();
		this.day5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 667;
		t.name = "0";
		t.touchThrough = true;
		t.width = 375;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.DrillOne5_i(),this.TaskOne5_i(),this.GMYZG_i(),this._Label57_i(),this.JCCLJSSC_i(),this._Label58_i(),this.WBLC_i(),this._Label59_i(),this.SQLCHL_i(),this._Label60_i(),this.SQHYHLBGESG_i(),this._Label61_i(),this._Label62_i(),this.TaskThree5_i(),this.TaskThree7_i(),this.TaskThree8_i(),this.TaskThree9_i(),this.DrillThree5_i(),this.DrillThree6_i(),this.DrillThree7_i(),this.DrillThree8_i(),this.tp14_i(),this.tp15_i(),this.tp16_i(),this.tp13_i(),this.tp12_i(),this.DJLQ5_i()];
		return t;
	};
	_proto.DrillOne5_i = function () {
		var t = new eui.Image();
		this.DrillOne5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 285;
		return t;
	};
	_proto.TaskOne5_i = function () {
		var t = new eui.Label();
		this.TaskOne5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "购买一只狗";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 290;
		return t;
	};
	_proto.GMYZG_i = function () {
		var t = new eui.Label();
		this.GMYZG = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 293;
		return t;
	};
	_proto._Label57_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 293;
		return t;
	};
	_proto.JCCLJSSC_i = function () {
		var t = new eui.Label();
		this.JCCLJSSC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 320;
		return t;
	};
	_proto._Label58_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/30";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 320;
		return t;
	};
	_proto.WBLC_i = function () {
		var t = new eui.Label();
		this.WBLC = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 352;
		return t;
	};
	_proto._Label59_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/6";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 352;
		return t;
	};
	_proto.SQLCHL_i = function () {
		var t = new eui.Label();
		this.SQLCHL = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 381;
		return t;
	};
	_proto._Label60_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/6";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 381;
		return t;
	};
	_proto.SQHYHLBGESG_i = function () {
		var t = new eui.Label();
		this.SQHYHLBGESG = t;
		t.size = 14;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 289;
		t.y = 411;
		return t;
	};
	_proto._Label61_i = function () {
		var t = new eui.Label();
		t.size = 14;
		t.text = "/20";
		t.textColor = 0x000000;
		t.x = 300;
		t.y = 411;
		return t;
	};
	_proto._Label62_i = function () {
		var t = new eui.Label();
		t.size = 12;
		t.text = "X 5";
		t.textColor = 0x000000;
		t.x = 251;
		t.y = 511;
		return t;
	};
	_proto.TaskThree5_i = function () {
		var t = new eui.Label();
		this.TaskThree5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "浇水、除虫、除虫累计30次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 320;
		return t;
	};
	_proto.TaskThree7_i = function () {
		var t = new eui.Label();
		this.TaskThree7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "挖宝6次";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 350;
		return t;
	};
	_proto.TaskThree8_i = function () {
		var t = new eui.Label();
		this.TaskThree8 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "收取6次葫芦";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 380;
		return t;
	};
	_proto.TaskThree9_i = function () {
		var t = new eui.Label();
		this.TaskThree9 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 14;
		t.text = "收取好友葫芦币共20个";
		t.textColor = 0x000000;
		t.x = 90;
		t.y = 410;
		return t;
	};
	_proto.DrillThree5_i = function () {
		var t = new eui.Image();
		this.DrillThree5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 24;
		t.x = 48;
		t.y = 315;
		return t;
	};
	_proto.DrillThree6_i = function () {
		var t = new eui.Image();
		this.DrillThree6 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "three@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 345;
		return t;
	};
	_proto.DrillThree7_i = function () {
		var t = new eui.Image();
		this.DrillThree7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "four@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 375;
		return t;
	};
	_proto.DrillThree8_i = function () {
		var t = new eui.Image();
		this.DrillThree8 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 24;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "five@3x_daoju_png";
		t.width = 24;
		t.x = 48;
		t.y = 405;
		return t;
	};
	_proto.tp14_i = function () {
		var t = new eui.Image();
		this.tp14 = t;
		t.height = 37;
		t.source = "zuanshichutou@3x_daoju_png";
		t.width = 40;
		t.x = 135;
		t.y = 489;
		return t;
	};
	_proto.tp15_i = function () {
		var t = new eui.Image();
		this.tp15 = t;
		t.height = 37;
		t.source = "mofateji@3x_daoju_png";
		t.width = 40;
		t.x = 180;
		t.y = 489;
		return t;
	};
	_proto.tp16_i = function () {
		var t = new eui.Image();
		this.tp16 = t;
		t.height = 37;
		t.source = "hulu@2x_png";
		t.width = 40;
		t.x = 224;
		t.y = 489;
		return t;
	};
	_proto.tp13_i = function () {
		var t = new eui.Image();
		this.tp13 = t;
		t.height = 37;
		t.source = "gaojigouliang@3x_daoju_png";
		t.width = 40;
		t.x = 90;
		t.y = 489;
		return t;
	};
	_proto.tp12_i = function () {
		var t = new eui.Image();
		this.tp12 = t;
		t.height = 37;
		t.source = "feiliao@3x_daoju_png";
		t.width = 40;
		t.x = 45;
		t.y = 489;
		return t;
	};
	_proto.DJLQ5_i = function () {
		var t = new eui.Button();
		this.DJLQ5 = t;
		t.enabled = false;
		t.height = 33;
		t.label = "";
		t.name = "0";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 52;
		t.x = 267;
		t.y = 502;
		t.skinName = TheFarmLobbySkin$Skin108;
		return t;
	};
	_proto.jieshaojiemian0_i = function () {
		var t = new eui.Group();
		this.jieshaojiemian0 = t;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.touchThrough = true;
		t.elementsContent = [this.DJJJMP0_i(),this.DJJJMP_neirou0_i()];
		return t;
	};
	_proto.DJJJMP0_i = function () {
		var t = new eui.Image();
		this.DJJJMP0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 37;
		t.source = "DJJJMP_png";
		t.visible = false;
		t.width = 126;
		t.x = 65;
		t.y = 174;
		return t;
	};
	_proto.DJJJMP_neirou0_i = function () {
		var t = new eui.Label();
		this.DJJJMP_neirou0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.size = 15;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 59;
		t.y = 182;
		return t;
	};
	_proto.OneDays_i = function () {
		var t = new eui.Image();
		this.OneDays = t;
		t.height = 27;
		t.source = "oneL@3x_png";
		t.width = 43;
		t.x = 39;
		t.y = 257;
		return t;
	};
	_proto.TowDays_i = function () {
		var t = new eui.Image();
		this.TowDays = t;
		t.height = 22;
		t.source = "twoT@3x_png";
		t.width = 43;
		t.x = 81.5;
		t.y = 257;
		return t;
	};
	_proto.ThreeDays_i = function () {
		var t = new eui.Image();
		this.ThreeDays = t;
		t.height = 22;
		t.source = "threeT@3x_png";
		t.width = 43;
		t.x = 123.5;
		t.y = 257;
		return t;
	};
	_proto.FourDays_i = function () {
		var t = new eui.Image();
		this.FourDays = t;
		t.height = 22;
		t.source = "fourT@3x_png";
		t.width = 43;
		t.x = 166;
		t.y = 257;
		return t;
	};
	_proto.FiveDays_i = function () {
		var t = new eui.Image();
		this.FiveDays = t;
		t.height = 22;
		t.source = "fiveJ@3x_png";
		t.width = 43;
		t.x = 208.5;
		t.y = 257;
		return t;
	};
	_proto.SixDays_i = function () {
		var t = new eui.Image();
		this.SixDays = t;
		t.height = 22;
		t.source = "sixT@3x_png";
		t.width = 43;
		t.x = 251.5;
		t.y = 258;
		return t;
	};
	_proto.SevenDays_i = function () {
		var t = new eui.Image();
		this.SevenDays = t;
		t.height = 22;
		t.source = "sevenT@3x_png";
		t.width = 43;
		t.x = 294.5;
		t.y = 258;
		return t;
	};
	_proto.address0_DT_i = function () {
		var t = new eui.Group();
		this.address0_DT = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.top = 0;
		t.visible = false;
		t.elementsContent = [this._Image233_i(),this._Image234_i(),this.receiver_address0_i(),this.receiver_name0_i(),this._Image235_i(),this.address_close0_i(),this._Label63_i(),this._Label64_i(),this._Label65_i(),this.receiver_number0_i(),this.address_yes0_i()];
		return t;
	};
	_proto._Image233_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		return t;
	};
	_proto._Image234_i = function () {
		var t = new eui.Image();
		t.height = 334;
		t.source = "beijing3_png";
		t.width = 294;
		t.x = 41;
		t.y = 142;
		return t;
	};
	_proto.receiver_address0_i = function () {
		var t = new eui.TextInput();
		this.receiver_address0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 95;
		t.prompt = "请填写详细收货地址";
		t.width = 176;
		t.x = 133;
		t.y = 303;
		return t;
	};
	_proto.receiver_name0_i = function () {
		var t = new eui.TextInput();
		this.receiver_name0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 26;
		t.prompt = "请填写收货人姓名";
		t.width = 176;
		t.x = 133;
		t.y = 216;
		return t;
	};
	_proto._Image235_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "tishi_png";
		t.width = 214;
		t.x = 86;
		t.y = 120;
		return t;
	};
	_proto.address_close0_i = function () {
		var t = new eui.Button();
		this.address_close0 = t;
		t.height = 37;
		t.label = "";
		t.width = 37;
		t.x = 310;
		t.y = 136;
		t.skinName = TheFarmLobbySkin$Skin109;
		return t;
	};
	_proto._Label63_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 15;
		t.text = "联系人";
		t.textColor = 0x013952;
		t.x = 66;
		t.y = 227;
		return t;
	};
	_proto._Label64_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 15;
		t.text = "联系电话";
		t.textColor = 0x013952;
		t.x = 66;
		t.y = 268;
		return t;
	};
	_proto._Label65_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 15;
		t.text = "联系地址";
		t.textColor = 0x013952;
		t.x = 66;
		t.y = 311;
		return t;
	};
	_proto.receiver_number0_i = function () {
		var t = new eui.TextInput();
		this.receiver_number0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 26;
		t.maxChars = 11;
		t.prompt = "请填写收货人联系方式";
		t.restrict = "\"0-9\"";
		t.width = 176;
		t.x = 133;
		t.y = 260;
		return t;
	};
	_proto.address_yes0_i = function () {
		var t = new eui.Button();
		this.address_yes0 = t;
		t.height = 36;
		t.label = "";
		t.width = 76;
		t.x = 150;
		t.y = 416;
		t.skinName = TheFarmLobbySkin$Skin110;
		return t;
	};
	_proto.TISHIYUJU_i = function () {
		var t = new eui.Image();
		this.TISHIYUJU = t;
		t.alpha = 1;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.source = "GMTSTK@3x_png";
		t.visible = false;
		t.width = 228;
		t.x = 69;
		t.y = 291;
		return t;
	};
	_proto.tishixinxi_i = function () {
		var t = new eui.Label();
		this.tishixinxi = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 36;
		t.size = 15;
		t.text = "您的奖励已放入仓库";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.visible = false;
		t.width = 211;
		t.x = 76;
		t.y = 297;
		return t;
	};
	return TheFarmLobbySkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/ToggleSwitchSkin.exml'] = window.skins.ToggleSwitchSkin = (function (_super) {
	__extends(ToggleSwitchSkin, _super);
	function ToggleSwitchSkin() {
		_super.call(this);
		this.skinParts = [];
		
		this.elementsContent = [this._Image1_i(),this._Image2_i()];
		this.states = [
			new eui.State ("up",
				[
					new eui.SetProperty("_Image1","source","off_png")
				])
			,
			new eui.State ("down",
				[
					new eui.SetProperty("_Image1","source","off_png")
				])
			,
			new eui.State ("disabled",
				[
					new eui.SetProperty("_Image1","source","off_png")
				])
			,
			new eui.State ("upAndSelected",
				[
					new eui.SetProperty("_Image2","horizontalCenter",18)
				])
			,
			new eui.State ("downAndSelected",
				[
					new eui.SetProperty("_Image2","horizontalCenter",18)
				])
			,
			new eui.State ("disabledAndSelected",
				[
					new eui.SetProperty("_Image2","horizontalCenter",18)
				])
		];
	}
	var _proto = ToggleSwitchSkin.prototype;

	_proto._Image1_i = function () {
		var t = new eui.Image();
		this._Image1 = t;
		t.source = "on_png";
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		this._Image2 = t;
		t.height = 27;
		t.left = 0;
		t.source = "handle_png";
		t.width = 27;
		t.y = 0;
		return t;
	};
	return ToggleSwitchSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/VScrollBarSkin.exml'] = window.skins.VScrollBarSkin = (function (_super) {
	__extends(VScrollBarSkin, _super);
	function VScrollBarSkin() {
		_super.call(this);
		this.skinParts = ["thumb"];
		
		this.minHeight = 20;
		this.minWidth = 8;
		this.elementsContent = [this.thumb_i()];
	}
	var _proto = VScrollBarSkin.prototype;

	_proto.thumb_i = function () {
		var t = new eui.Image();
		this.thumb = t;
		t.height = 30;
		t.horizontalCenter = 0;
		t.scale9Grid = new egret.Rectangle(3,3,2,2);
		t.source = "roundthumb_png";
		t.width = 8;
		return t;
	};
	return VScrollBarSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/VSliderSkin.exml'] = window.skins.VSliderSkin = (function (_super) {
	__extends(VSliderSkin, _super);
	function VSliderSkin() {
		_super.call(this);
		this.skinParts = ["track","thumb"];
		
		this.height = 273;
		this.minHeight = 30;
		this.minWidth = 25;
		this.elementsContent = [this.track_i(),this.thumb_i()];
	}
	var _proto = VSliderSkin.prototype;

	_proto.track_i = function () {
		var t = new eui.Image();
		this.track = t;
		t.percentHeight = 100;
		t.horizontalCenter = 0;
		t.scale9Grid = new egret.Rectangle(1,1,4,4);
		t.source = "track_png";
		t.width = 7;
		return t;
	};
	_proto.thumb_i = function () {
		var t = new eui.Image();
		this.thumb = t;
		t.horizontalCenter = 0;
		t.source = "thumb_png";
		return t;
	};
	return VSliderSkin;
})(eui.Skin);