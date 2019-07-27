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
                generateEUI.skins = {"eui.Button":"resource/eui_skins/ButtonSkin.exml","eui.CheckBox":"resource/eui_skins/CheckBoxSkin.exml","eui.HScrollBar":"resource/eui_skins/HScrollBarSkin.exml","eui.HSlider":"resource/eui_skins/HSliderSkin.exml","eui.Panel":"resource/eui_skins/PanelSkin.exml","eui.TextInput":"resource/eui_skins/TextInputSkin.exml","eui.ProgressBar":"resource/eui_skins/ProgressBarSkin.exml","eui.RadioButton":"resource/eui_skins/RadioButtonSkin.exml","eui.Scroller":"resource/eui_skins/ScrollerSkin.exml","eui.ToggleSwitch":"resource/eui_skins/ToggleSwitchSkin.exml","eui.VScrollBar":"resource/eui_skins/VScrollBarSkin.exml","eui.VSlider":"resource/eui_skins/VSliderSkin.exml","eui.ItemRenderer":"resource/eui_skins/ItemRendererSkin.exml","TheFarmLobby":"resource/eui_skins/TheFarmLobby.exml","package":"resource/eui_skins/package.exml","Package":"resource/eui_skins/Package.exml","a":"resource/eui_skins/a.exml","Toast":"resource/eui_skins/Toast.exml"};generateEUI.paths['resource/eui_skins/ButtonSkin.exml'] = window.skins.ButtonSkin = (function (_super) {
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
		t.size = 20;
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
		t.source = "juxing6789_png";
		t.verticalCenter = 0;
		t.percentWidth = 100;
		return t;
	};
	_proto.thumb_i = function () {
		var t = new eui.Image();
		this.thumb = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "juxing_png";
		t.top = 0;
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
	var SomeoneRoom$Skin1 = 	(function (_super) {
		__extends(SomeoneRoom$Skin1, _super);
		function SomeoneRoom$Skin1() {
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
		var _proto = SomeoneRoom$Skin1.prototype;

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
		return SomeoneRoom$Skin1;
	})(eui.Skin);

	var SomeoneRoom$Skin2 = 	(function (_super) {
		__extends(SomeoneRoom$Skin2, _super);
		function SomeoneRoom$Skin2() {
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
		var _proto = SomeoneRoom$Skin2.prototype;

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
		return SomeoneRoom$Skin2;
	})(eui.Skin);

	function SomeoneRoom() {
		_super.call(this);
		this.skinParts = ["Return0","farmland20","farmland21","farmland22","farmland23","farmland24","farmland25","farmland26","farmland27","farmland28","land20","land21","land22","land23","land24","land25","land26","land27","land28","rare20","rare21","rare22","rare23","rare24","rare25","rare26","rare27","rare28","ripe20","ripe21","ripe22","ripe23","ripe24","ripe25","ripe26","ripe27","ripe28","LandBuy0","dog0","dogbowl1","zhai0","zhai1","zhai2","zhai3","zhai4","zhai5","zhai6","zhai7","zhai8","NameBackground0","LV0","Name0","HeadPortrait0","Money0"];
		
		this.height = 1000;
		this.width = 500;
		this.elementsContent = [this._Scroller1_i(),this.NameBackground0_i(),this.LV0_i(),this.Name0_i(),this.HeadPortrait0_i(),this.Money0_i(),this._Button1_i()];
	}
	var _proto = SomeoneRoom.prototype;

	_proto._Scroller1_i = function () {
		var t = new eui.Scroller();
		t.bounces = false;
		t.height = 1000;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.viewport = this._Group2_i();
		return t;
	};
	_proto._Group2_i = function () {
		var t = new eui.Group();
		t.y = 2;
		t.elementsContent = [this._Image1_i(),this._Group1_i()];
		return t;
	};
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
	_proto._Group1_i = function () {
		var t = new eui.Group();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.Return0_i(),this.farmland20_i(),this.farmland21_i(),this.farmland22_i(),this.farmland23_i(),this.farmland24_i(),this.farmland25_i(),this.farmland26_i(),this.farmland27_i(),this.farmland28_i(),this.land20_i(),this.land21_i(),this.land22_i(),this.land23_i(),this.land24_i(),this.land25_i(),this.land26_i(),this.land27_i(),this.land28_i(),this.rare20_i(),this.rare21_i(),this.rare22_i(),this.rare23_i(),this.rare24_i(),this.rare25_i(),this.rare26_i(),this.rare27_i(),this.rare28_i(),this.ripe20_i(),this.ripe21_i(),this.ripe22_i(),this.ripe23_i(),this.ripe24_i(),this.ripe25_i(),this.ripe26_i(),this.ripe27_i(),this.ripe28_i(),this.LandBuy0_i(),this.dog0_i(),this.dogbowl1_i(),this.zhai0_i(),this.zhai1_i(),this.zhai2_i(),this.zhai3_i(),this.zhai4_i(),this.zhai5_i(),this.zhai6_i(),this.zhai7_i(),this.zhai8_i(),this._Image2_i(),this._Image3_i()];
		return t;
	};
	_proto.Return0_i = function () {
		var t = new eui.Button();
		this.Return0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 204;
		t.label = "";
		t.touchEnabled = false;
		t.width = 300;
		t.x = 1265;
		t.y = 735;
		t.skinName = SomeoneRoom$Skin1;
		return t;
	};
	_proto.farmland20_i = function () {
		var t = new eui.Image();
		this.farmland20 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1210;
		t.y = 980;
		return t;
	};
	_proto.farmland21_i = function () {
		var t = new eui.Image();
		this.farmland21 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1140;
		t.y = 1030;
		return t;
	};
	_proto.farmland22_i = function () {
		var t = new eui.Image();
		this.farmland22 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1287;
		t.y = 1030;
		return t;
	};
	_proto.farmland23_i = function () {
		var t = new eui.Image();
		this.farmland23 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1070;
		t.y = 1090;
		return t;
	};
	_proto.farmland24_i = function () {
		var t = new eui.Image();
		this.farmland24 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1210;
		t.y = 1090;
		return t;
	};
	_proto.farmland25_i = function () {
		var t = new eui.Image();
		this.farmland25 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1350;
		t.y = 1090;
		return t;
	};
	_proto.farmland26_i = function () {
		var t = new eui.Image();
		this.farmland26 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1140;
		t.y = 1150;
		return t;
	};
	_proto.farmland27_i = function () {
		var t = new eui.Image();
		this.farmland27 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1280;
		t.y = 1150;
		return t;
	};
	_proto.farmland28_i = function () {
		var t = new eui.Image();
		this.farmland28 = t;
		t.height = 100;
		t.source = "weizhongzhi_png";
		t.width = 100;
		t.x = 1210;
		t.y = 1200;
		return t;
	};
	_proto.land20_i = function () {
		var t = new eui.Image();
		this.land20 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 500;
		return t;
	};
	_proto.land21_i = function () {
		var t = new eui.Image();
		this.land21 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 500;
		return t;
	};
	_proto.land22_i = function () {
		var t = new eui.Image();
		this.land22 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 500;
		return t;
	};
	_proto.land23_i = function () {
		var t = new eui.Image();
		this.land23 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 600;
		return t;
	};
	_proto.land24_i = function () {
		var t = new eui.Image();
		this.land24 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 600;
		return t;
	};
	_proto.land25_i = function () {
		var t = new eui.Image();
		this.land25 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 600;
		return t;
	};
	_proto.land26_i = function () {
		var t = new eui.Image();
		this.land26 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 700;
		return t;
	};
	_proto.land27_i = function () {
		var t = new eui.Image();
		this.land27 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 700;
		return t;
	};
	_proto.land28_i = function () {
		var t = new eui.Image();
		this.land28 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 700;
		return t;
	};
	_proto.rare20_i = function () {
		var t = new eui.Image();
		this.rare20 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 450;
		return t;
	};
	_proto.rare21_i = function () {
		var t = new eui.Image();
		this.rare21 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 450;
		return t;
	};
	_proto.rare22_i = function () {
		var t = new eui.Image();
		this.rare22 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 450;
		return t;
	};
	_proto.rare23_i = function () {
		var t = new eui.Image();
		this.rare23 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 550;
		return t;
	};
	_proto.rare24_i = function () {
		var t = new eui.Image();
		this.rare24 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 550;
		return t;
	};
	_proto.rare25_i = function () {
		var t = new eui.Image();
		this.rare25 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 550;
		return t;
	};
	_proto.rare26_i = function () {
		var t = new eui.Image();
		this.rare26 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 650;
		return t;
	};
	_proto.rare27_i = function () {
		var t = new eui.Image();
		this.rare27 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 650;
		return t;
	};
	_proto.rare28_i = function () {
		var t = new eui.Image();
		this.rare28 = t;
		t.height = 150;
		t.source = "pewter_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 650;
		return t;
	};
	_proto.ripe20_i = function () {
		var t = new eui.Image();
		this.ripe20 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 400;
		return t;
	};
	_proto.ripe21_i = function () {
		var t = new eui.Image();
		this.ripe21 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 400;
		return t;
	};
	_proto.ripe22_i = function () {
		var t = new eui.Image();
		this.ripe22 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 400;
		return t;
	};
	_proto.ripe23_i = function () {
		var t = new eui.Image();
		this.ripe23 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 500;
		return t;
	};
	_proto.ripe24_i = function () {
		var t = new eui.Image();
		this.ripe24 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 500;
		return t;
	};
	_proto.ripe25_i = function () {
		var t = new eui.Image();
		this.ripe25 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 500;
		return t;
	};
	_proto.ripe26_i = function () {
		var t = new eui.Image();
		this.ripe26 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 200;
		t.y = 600;
		return t;
	};
	_proto.ripe27_i = function () {
		var t = new eui.Image();
		this.ripe27 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 600;
		return t;
	};
	_proto.ripe28_i = function () {
		var t = new eui.Image();
		this.ripe28 = t;
		t.height = 200;
		t.source = "yellow_png";
		t.visible = false;
		t.width = 100;
		t.x = 400;
		t.y = 600;
		return t;
	};
	_proto.LandBuy0_i = function () {
		var t = new eui.Image();
		this.LandBuy0 = t;
		t.height = 100;
		t.source = "zhongzhi@2x(1)_png";
		t.width = 100;
		t.x = 1210;
		t.y = 1160;
		return t;
	};
	_proto.dog0_i = function () {
		var t = new eui.Image();
		this.dog0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "erha1_png";
		t.width = 100;
		t.x = 950;
		t.y = 1290;
		return t;
	};
	_proto.dogbowl1_i = function () {
		var t = new eui.Image();
		this.dogbowl1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "goucao@2x_png";
		t.width = 88;
		t.x = 1072;
		t.y = 940;
		return t;
	};
	_proto.zhai0_i = function () {
		var t = new eui.Image();
		this.zhai0 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 300;
		return t;
	};
	_proto.zhai1_i = function () {
		var t = new eui.Image();
		this.zhai1 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 300;
		return t;
	};
	_proto.zhai2_i = function () {
		var t = new eui.Image();
		this.zhai2 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415;
		t.y = 300;
		return t;
	};
	_proto.zhai3_i = function () {
		var t = new eui.Image();
		this.zhai3 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 400;
		return t;
	};
	_proto.zhai4_i = function () {
		var t = new eui.Image();
		this.zhai4 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 400;
		return t;
	};
	_proto.zhai5_i = function () {
		var t = new eui.Image();
		this.zhai5 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415;
		t.y = 400;
		return t;
	};
	_proto.zhai6_i = function () {
		var t = new eui.Image();
		this.zhai6 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 500;
		return t;
	};
	_proto.zhai7_i = function () {
		var t = new eui.Image();
		this.zhai7 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 500;
		return t;
	};
	_proto.zhai8_i = function () {
		var t = new eui.Image();
		this.zhai8 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415;
		t.y = 500;
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 150;
		t.source = "yutang@2x_png";
		t.width = 150;
		t.x = 545;
		t.y = 1350;
		return t;
	};
	_proto._Image3_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 400;
		t.source = "wabao@2x_png";
		t.width = 500;
		t.x = 500;
		t.y = 750;
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
		t.skinName = SomeoneRoom$Skin2;
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
		this.elementsContent = [this._Image1_i(),this._Rect1_i(),this.textDisplay_i()];
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

	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.percentHeight = 100;
		t.scale9Grid = new egret.Rectangle(1,3,8,8);
		t.source = "button_up_png";
		t.percentWidth = 100;
		return t;
	};
	_proto._Rect1_i = function () {
		var t = new eui.Rect();
		t.fillColor = 0xcec6c6;
		t.percentHeight = 100;
		t.percentWidth = 100;
		return t;
	};
	_proto.textDisplay_i = function () {
		var t = new eui.EditableText();
		this.textDisplay = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = "8";
		t.left = "12";
		t.multiline = true;
		t.right = "11";
		t.size = 20;
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
		t.size = 14;
		t.text = "";
		t.textColor = 0xa9a9a9;
		t.top = 9;
		t.touchEnabled = false;
		t.percentWidth = 100;
		return t;
	};
	return TextInputSkin;
})(eui.Skin);generateEUI.paths['resource/eui_skins/TheFarmLobby.exml'] = window.TheFarmLobbySkin = (function (_super) {
	__extends(TheFarmLobbySkin, _super);
	var TheFarmLobbySkin$Skin3 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin3, _super);
		function TheFarmLobbySkin$Skin3() {
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
		var _proto = TheFarmLobbySkin$Skin3.prototype;

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
		return TheFarmLobbySkin$Skin3;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin4 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin4, _super);
		function TheFarmLobbySkin$Skin4() {
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
		var _proto = TheFarmLobbySkin$Skin4.prototype;

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
		return TheFarmLobbySkin$Skin4;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin5 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin5, _super);
		function TheFarmLobbySkin$Skin5() {
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
		var _proto = TheFarmLobbySkin$Skin5.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "bjx_png";
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
		return TheFarmLobbySkin$Skin5;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin6 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin6, _super);
		function TheFarmLobbySkin$Skin6() {
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
		var _proto = TheFarmLobbySkin$Skin6.prototype;

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
		return TheFarmLobbySkin$Skin6;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin7 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin7, _super);
		function TheFarmLobbySkin$Skin7() {
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
		var _proto = TheFarmLobbySkin$Skin7.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "haoyou@3x_png";
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
		return TheFarmLobbySkin$Skin7;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin8 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin8, _super);
		function TheFarmLobbySkin$Skin8() {
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
		var _proto = TheFarmLobbySkin$Skin8.prototype;

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
		return TheFarmLobbySkin$Skin8;
	})(eui.Skin);

	var TheFarmLobbySkin$Skin9 = 	(function (_super) {
		__extends(TheFarmLobbySkin$Skin9, _super);
		function TheFarmLobbySkin$Skin9() {
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
		var _proto = TheFarmLobbySkin$Skin9.prototype;

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
		return TheFarmLobbySkin$Skin9;
	})(eui.Skin);

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
			t.source = "daoju1_png";
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
			t.source = "ChemicalFertilizer1_png";
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
			t.source = "ChemicalFertilizer2_png";
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
			t.source = "ChemicalFertilizer3_png";
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
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90),
						new eui.SetProperty("_Image1","source","×_png")
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","×_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin18.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","baocun@2x(8)_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin20.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "baocun@2x_png";
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
			t.source = "bianji@2x_png";
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
			t.source = "feiliao2_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","yes_png")
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "yes_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
			t.source = "baijinchutou@2x_png";
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
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin29.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "zuanshichutou@2x_png";
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
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin31.prototype;

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
						new eui.SetProperty("_Image1","source","×_png")
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
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
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
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
			t.source = "×_png";
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
		var _proto = TheFarmLobbySkin$Skin40.prototype;

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
						new eui.SetProperty("_Image1","source","goumai(1)_png")
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin41.prototype;

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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			t.horizontalCenter = 0;
			t.source = "×_png";
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
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin43.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			t.percentHeight = 100;
			t.source = "duihuanjilu_png";
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
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","duihuan1_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin44.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "duihuan(1)_png";
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
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","duihuan1_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin45.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "duihuan(1)_png";
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
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","duihuan1_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin46.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "duihuan(1)_png";
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
						new eui.SetProperty("_Image1","source","×_png")
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
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
			t.source = "yiwen_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
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
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
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
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
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
			t.source = "×_png";
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
			t.percentHeight = 100;
			t.source = "jieshou_png";
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
			t.percentHeight = 100;
			t.source = "jujue_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
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
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","queding_png"),
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
			t.source = "queding_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
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
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
						new eui.SetProperty("_Image1","source","daiwancheng_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin65.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "lingqu_png";
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
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","daiwancheng_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin66.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "qiandaolingqu @3x_png";
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
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","daiwancheng_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin67.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "lingqu_png";
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
					])
				,
				new eui.State ("disabled",
					[
						new eui.SetProperty("_Image1","source","daiwancheng_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin68.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "lingqu_png";
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
						new eui.SetProperty("_Image1","source","daiwancheng_png")
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin69.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.source = "lingqu_png";
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
						new eui.SetProperty("_Image1","source","×_png"),
						new eui.SetProperty("_Image1","percentWidth",90),
						new eui.SetProperty("_Image1","percentHeight",90)
					])
				,
				new eui.State ("disabled",
					[
					])
			];
		}
		var _proto = TheFarmLobbySkin$Skin70.prototype;

		_proto._Image1_i = function () {
			var t = new eui.Image();
			this._Image1 = t;
			t.percentHeight = 100;
			t.horizontalCenter = 0;
			t.source = "×_png";
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
		return TheFarmLobbySkin$Skin70;
	})(eui.Skin);

	function TheFarmLobbySkin() {
		_super.call(this);
		this.skinParts = ["BG","rotate0","Return","farmland1","farmland2","farmland3","farmland4","farmland5","farmland6","farmland7","farmland8","farmland9","LandBuy","land1","land2","land3","land4","land5","land6","land7","land8","land9","rare1","rare2","rare3","rare4","rare5","rare6","rare7","rare8","rare9","ripe1","ripe2","ripe3","ripe4","ripe5","ripe6","ripe7","ripe8","ripe9","dogbowl0","zhai1","zhai2","zhai3","zhai4","zhai5","zhai6","zhai7","zhai8","zhai9","IndianArrowheads","rail","dog","rotate1","rollxd","roll","HeadPortrait","NameBackground","Name","LV","HebdomadTask","Install","Friend","Money","Help","Task","Prop","dark","hint_text","dim","quit","watering","weeding","deinsectization","RipeTime1","RipeTime2","RipeTime3","RipeTime4","RipeTime5","RipeTime6","RipeTime7","RipeTime8","RipeTime9","manure1","manure2","manure3","AggregateScheduling1","Scheduling1","Scheduling_Number1","AggregateScheduling9","Scheduling9","Scheduling_Number9","AggregateScheduling2","Scheduling2","Scheduling_Number2","AggregateScheduling3","Scheduling3","Scheduling_Number3","AggregateScheduling4","Scheduling4","Scheduling_Number4","AggregateScheduling5","Scheduling5","Scheduling_Number5","AggregateScheduling6","Scheduling6","Scheduling_Number6","AggregateScheduling7","Scheduling7","Scheduling_Number7","AggregateScheduling8","Scheduling8","Scheduling_Number8","condition","Music","SoundEffect","Options_close","Options","MyCenter_close","ChangeName","modification","CurrentTitleLevel","detail","TheCurrentMoney","TotalMoney","TheNextLevel","TotalMoneyTow","TheNextLevelMoney","fertilizer","plan","schedule","Money_center","MyCenter","Designation_close","Designation","TheIndianArrowheads_close","Goldhoe","IndianArrowheadsNumber","Indian_arrowheads_record1","inform_frame","inform_close","inform","Log_message","Indian_arrowheads_record2_close","Indian_arrowheads_record2","TheIndianArrowheads","ForRecord_close","money","GoAndSee","record_BJ","record","record_Time","RecordBox","RecordStrip","ForRecord","BrowserHelpMenu_close","HelpText","HelpText0","HelpText1","HelpText2","RaiseMoney","Water","Seed","PetPig","BrowserHelpMenu","PropsFor_close","ContentProps","Conversion","prop_money","PropsRecord","consume","function","seed","petattack","warehouse","ConsumeGoods","cry","WarehouseGoods","ConsumeGoods_close","buy_text","ConsumeGoods_Subtract","ConsumeGoods_Plus","PurchaseQuantity","purchase0","price","ConsumeGoods_Buy","PropsOrder_close","OrderForm","Indent","PropsOrder","PropsClass","Change","tu1","tu2","tu3","secret1","secret2","secret3","conversion_presenter_1","conversion_presenter_2","conversion_presenter_3","Conversion_money","Modellpause","Bill","All","Obligation","ForShipping","HasBeenShipped","indent_particulars","Indent0","MyOrder","MyName","Comment","CommentTime","DiscussX","Discuss","ChangeTheOrder_close","xylophyta0","TimeCapsule0","DeliverGoods0","ExchangeForm","exchange","ChangeTheOrder","deficiency_text","shut1","deficiency","shut2","succeed","discuss_text","discuss_tu","discuss_yes","discuss_close","discuss","Select_category_close","Xtu","Xname","Xshu","Select_category","receiver_address","receiver_name","address_close","receiver_number","address_yes","address","hint3_colse","hint3_yes","hint3","ConversionGoods","commodity","Best","Attention","Pick","REGISTRATION","PrivateLetter","AddFriend","Parameters","friend_money","friend_close","figure","MeetPeople","PeopleChat","ChatTools","ChatFrame","ChatIcon","Send","Chitchat","Moniker","SendTo","Status","ApplyFor","FindMyPhone_close","PhoneNumber","Find","Rank","ChatHead","FaceIcon","BestRating","Addition","LevenshteinDistance","PlayerInformation","PlayerList","FindMyPhone","FriendSet_close","MobileSearch","RecommendationFriend","FriendSet","friend","TASK_close","Task2","HebdomadTask1","TASK","DeadlineTASK_close","TaskOne","TaskThree","OneDays_task2","DrillThree","AwardFour","AwardThree","AwardTow","get","OneDays_task1","day","DrillOne0","TaskOne0","TowDays_task1","TaskThree0","TowDays_task2","DrillThree0","AwardFour0","AwardTow0","AwardThree0","get0","day0","TaskThree1","ThreeDays_task","DrillThree1","AwardFour1","AwardThree1","AwardTow1","get1","day1","leij","FourDays_task","DrillTow2","AwardFour2","AwardThree2","AwardTow2","get2","day2","FiveDays_task","jiao","DrillTow3","AwardThree3","AwardTow3","get3","day3","SixDays_task1","DrillOne4","TaskOne4","TaskThree4","SixDays_task2","DrillThree4","AwardFour4","AwardThree4","AwardTow4","get4","day4","DrillOne5","TaskOne5","TaskThree5","DrillThree5","AwardFour5","AwardThree5","AwardTow5","get5","day5","OneDays","TowDays","ThreeDays","FourDays","FiveDays","SixDays","SevenDays","DeadlineTASK"];
		
		this.height = 1000;
		this.width = 500;
		this.elementsContent = [this.roll_i(),this.HeadPortrait_i(),this.NameBackground_i(),this.Name_i(),this._Button1_i(),this._Image5_i(),this._Image6_i(),this.LV_i(),this.HebdomadTask_i(),this.Install_i(),this.Friend_i(),this.Money_i(),this.Help_i(),this.Task_i(),this.Prop_i(),this.dark_i(),this.hint_text_i(),this.condition_i(),this.Options_i(),this.MyCenter_i(),this.Designation_i(),this.TheIndianArrowheads_i(),this.ForRecord_i(),this.BrowserHelpMenu_i(),this.commodity_i(),this.friend_i(),this.TASK_i(),this.DeadlineTASK_i()];
	}
	var _proto = TheFarmLobbySkin.prototype;

	_proto.roll_i = function () {
		var t = new eui.Scroller();
		this.roll = t;
		t.alpha = 1;
		t.bounces = false;
		t.enabled = true;
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
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 1070.91;
		t.width = 768.48;
		t.y = -4;
		t.elementsContent = [this.BG_i(),this._Image1_i(),this.rotate0_i(),this.Return_i(),this.farmland1_i(),this.farmland2_i(),this.farmland3_i(),this.farmland4_i(),this.farmland5_i(),this.farmland6_i(),this.farmland7_i(),this.farmland8_i(),this.farmland9_i(),this.LandBuy_i(),this.land1_i(),this.land2_i(),this.land3_i(),this.land4_i(),this.land5_i(),this.land6_i(),this.land7_i(),this.land8_i(),this.land9_i(),this.rare1_i(),this.rare2_i(),this.rare3_i(),this.rare4_i(),this.rare5_i(),this.rare6_i(),this.rare7_i(),this.rare8_i(),this.rare9_i(),this.ripe1_i(),this.ripe2_i(),this.ripe3_i(),this.ripe4_i(),this.ripe5_i(),this.ripe6_i(),this.ripe7_i(),this.ripe8_i(),this.ripe9_i(),this.dogbowl0_i(),this.zhai1_i(),this.zhai2_i(),this.zhai3_i(),this.zhai4_i(),this.zhai5_i(),this.zhai6_i(),this.zhai7_i(),this.zhai8_i(),this.zhai9_i(),this.IndianArrowheads_i(),this._Image2_i(),this.rail_i(),this.dog_i(),this._Image3_i(),this.rotate1_i(),this._Image4_i()];
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
	_proto._Image1_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
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
		t.source = "fengye@3x_png";
		t.width = 62;
		t.x = 961.24;
		t.y = 624.57;
		return t;
	};
	_proto.Return_i = function () {
		var t = new eui.Button();
		this.Return = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 300;
		t.label = "";
		t.width = 300;
		t.x = 1265;
		t.y = 735;
		t.skinName = TheFarmLobbySkin$Skin3;
		return t;
	};
	_proto.farmland1_i = function () {
		var t = new eui.Image();
		this.farmland1 = t;
		t.height = 75;
		t.rotation = 0;
		t.source = "weizhongzhi_png";
		t.width = 120;
		t.x = 1221.26;
		t.y = 1062.73;
		return t;
	};
	_proto.farmland2_i = function () {
		var t = new eui.Image();
		this.farmland2 = t;
		t.height = 75;
		t.rotation = 0;
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
		t.source = "gouliang 3x_png";
		t.width = 88;
		t.x = 1072;
		t.y = 1322;
		return t;
	};
	_proto.zhai1_i = function () {
		var t = new eui.Image();
		this.zhai1 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 300;
		return t;
	};
	_proto.zhai2_i = function () {
		var t = new eui.Image();
		this.zhai2 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 300;
		return t;
	};
	_proto.zhai3_i = function () {
		var t = new eui.Image();
		this.zhai3 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415;
		t.y = 300;
		return t;
	};
	_proto.zhai4_i = function () {
		var t = new eui.Image();
		this.zhai4 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 400;
		return t;
	};
	_proto.zhai5_i = function () {
		var t = new eui.Image();
		this.zhai5 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 400;
		return t;
	};
	_proto.zhai6_i = function () {
		var t = new eui.Image();
		this.zhai6 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415;
		t.y = 400;
		return t;
	};
	_proto.zhai7_i = function () {
		var t = new eui.Image();
		this.zhai7 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 215;
		t.y = 500;
		return t;
	};
	_proto.zhai8_i = function () {
		var t = new eui.Image();
		this.zhai8 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 315;
		t.y = 500;
		return t;
	};
	_proto.zhai9_i = function () {
		var t = new eui.Image();
		this.zhai9 = t;
		t.height = 70;
		t.source = "pick_png";
		t.visible = false;
		t.width = 70;
		t.x = 415;
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
		t.touchChildren = true;
		t.width = 500;
		t.x = 500;
		t.y = 750;
		t.skinName = TheFarmLobbySkin$Skin4;
		return t;
	};
	_proto._Image2_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 150;
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
		t.source = "liba@3x_png";
		t.width = 506;
		t.x = 1010;
		t.y = 970;
		return t;
	};
	_proto.dog_i = function () {
		var t = new eui.Image();
		this.dog = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 80;
		t.rotation = 0;
		t.source = "erha@3x_png";
		t.width = 80;
		t.x = 996.72;
		t.y = 1288.06;
		return t;
	};
	_proto._Image3_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "fengche@3x_png";
		t.width = 74;
		t.x = 410;
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
		t.source = "fengye@3x_png";
		t.width = 62;
		t.x = 461.24;
		t.y = 616.57;
		return t;
	};
	_proto._Image4_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 300;
		t.source = "2@3x_png";
		t.visible = false;
		t.width = 1800;
		t.x = 0;
		t.y = 356;
		return t;
	};
	_proto.HeadPortrait_i = function () {
		var t = new eui.Image();
		this.HeadPortrait = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "white2_png";
		t.width = 100;
		t.x = 10;
		t.y = 10;
		return t;
	};
	_proto.NameBackground_i = function () {
		var t = new eui.Image();
		this.NameBackground = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 45;
		t.source = "juxing(20)_png";
		t.width = 100;
		t.x = 10;
		t.y = 130;
		return t;
	};
	_proto.Name_i = function () {
		var t = new eui.Label();
		this.Name = t;
		t.anchorOffsetX = 0;
		t.size = 24;
		t.text = "一";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 100;
		t.x = 16;
		t.y = 140;
		return t;
	};
	_proto._Button1_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.height = 60;
		t.label = "";
		t.width = 160;
		t.x = 300;
		t.y = 21;
		t.skinName = TheFarmLobbySkin$Skin5;
		return t;
	};
	_proto._Image5_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "hulu_png";
		t.width = 40;
		t.x = 295.44;
		t.y = 24;
		return t;
	};
	_proto._Image6_i = function () {
		var t = new eui.Image();
		t.height = 56;
		t.source = "gengduo@3x_png";
		t.width = 60;
		t.x = 400;
		t.y = 22;
		return t;
	};
	_proto.LV_i = function () {
		var t = new eui.Image();
		this.LV = t;
		t.height = 50;
		t.source = "";
		t.width = 150;
		t.x = 130;
		t.y = 24;
		return t;
	};
	_proto.HebdomadTask_i = function () {
		var t = new eui.Button();
		this.HebdomadTask = t;
		t.anchorOffsetX = 0;
		t.label = "七天任务";
		t.width = 60;
		t.x = 121.27;
		t.y = 214.12;
		return t;
	};
	_proto.Install_i = function () {
		var t = new eui.Button();
		this.Install = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 80;
		t.label = "";
		t.width = 60;
		t.x = 424.12;
		t.y = 89.39;
		t.skinName = TheFarmLobbySkin$Skin6;
		return t;
	};
	_proto.Friend_i = function () {
		var t = new eui.Button();
		this.Friend = t;
		t.anchorOffsetX = 0;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 20;
		t.y = 188;
		t.skinName = TheFarmLobbySkin$Skin7;
		return t;
	};
	_proto.Money_i = function () {
		var t = new eui.Label();
		this.Money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 60;
		t.text = "1000";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 160;
		t.x = 293.94;
		t.y = 21;
		return t;
	};
	_proto.Help_i = function () {
		var t = new eui.Button();
		this.Help = t;
		t.anchorOffsetY = 0;
		t.height = 80;
		t.label = "";
		t.width = 60;
		t.x = 424.12;
		t.y = 167.39;
		t.skinName = TheFarmLobbySkin$Skin8;
		return t;
	};
	_proto.Task_i = function () {
		var t = new eui.Button();
		this.Task = t;
		t.enabled = true;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 20;
		t.y = 327.12;
		t.skinName = TheFarmLobbySkin$Skin9;
		return t;
	};
	_proto.Prop_i = function () {
		var t = new eui.Button();
		this.Prop = t;
		t.anchorOffsetX = 0;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 20;
		t.y = 258.12;
		t.skinName = TheFarmLobbySkin$Skin10;
		return t;
	};
	_proto.dark_i = function () {
		var t = new eui.Image();
		this.dark = t;
		t.bottom = 0;
		t.left = -3;
		t.right = 3;
		t.source = "TouMingBeiJing_png";
		t.top = 0;
		t.visible = false;
		return t;
	};
	_proto.hint_text_i = function () {
		var t = new eui.Label();
		this.hint_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 151.21;
		t.size = 50;
		t.text = "请选择要种植的土地";
		t.visible = false;
		t.width = 489.15;
		t.x = 9.06;
		t.y = 134;
		return t;
	};
	_proto.condition_i = function () {
		var t = new eui.Group();
		this.condition = t;
		t.anchorOffsetY = 0;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this.dim_i(),this.quit_i(),this.watering_i(),this.weeding_i(),this.deinsectization_i(),this.RipeTime1_i(),this.RipeTime2_i(),this.RipeTime3_i(),this.RipeTime4_i(),this.RipeTime5_i(),this.RipeTime6_i(),this.RipeTime7_i(),this.RipeTime8_i(),this.RipeTime9_i(),this.manure1_i(),this.manure2_i(),this.manure3_i(),this.AggregateScheduling1_i(),this.Scheduling1_i(),this.Scheduling_Number1_i(),this.AggregateScheduling9_i(),this.Scheduling9_i(),this.Scheduling_Number9_i(),this.AggregateScheduling2_i(),this.Scheduling2_i(),this.Scheduling_Number2_i(),this.AggregateScheduling3_i(),this.Scheduling3_i(),this.Scheduling_Number3_i(),this.AggregateScheduling4_i(),this.Scheduling4_i(),this.Scheduling_Number4_i(),this.AggregateScheduling5_i(),this.Scheduling5_i(),this.Scheduling_Number5_i(),this.AggregateScheduling6_i(),this.Scheduling6_i(),this.Scheduling_Number6_i(),this.AggregateScheduling7_i(),this.Scheduling7_i(),this.Scheduling_Number7_i(),this.AggregateScheduling8_i(),this.Scheduling8_i(),this.Scheduling_Number8_i()];
		return t;
	};
	_proto.dim_i = function () {
		var t = new eui.Image();
		this.dim = t;
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.quit_i = function () {
		var t = new eui.Button();
		this.quit = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 114;
		t.y = 246;
		t.skinName = TheFarmLobbySkin$Skin11;
		return t;
	};
	_proto.watering_i = function () {
		var t = new eui.Button();
		this.watering = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 71;
		t.y = 675;
		t.skinName = TheFarmLobbySkin$Skin12;
		return t;
	};
	_proto.weeding_i = function () {
		var t = new eui.Button();
		this.weeding = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 328;
		t.y = 512.79;
		t.skinName = TheFarmLobbySkin$Skin13;
		return t;
	};
	_proto.deinsectization_i = function () {
		var t = new eui.Button();
		this.deinsectization = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 347.4;
		t.y = 669;
		t.skinName = TheFarmLobbySkin$Skin14;
		return t;
	};
	_proto.RipeTime1_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 234;
		t.y = 453.58;
		return t;
	};
	_proto.RipeTime2_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 254;
		t.y = 473.58;
		return t;
	};
	_proto.RipeTime3_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 264;
		t.y = 483.58;
		return t;
	};
	_proto.RipeTime4_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 274;
		t.y = 493.58;
		return t;
	};
	_proto.RipeTime5_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 284;
		t.y = 503.58;
		return t;
	};
	_proto.RipeTime6_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime6 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 294;
		t.y = 513.5799999999999;
		return t;
	};
	_proto.RipeTime7_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime7 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 304;
		t.y = 523.5799999999999;
		return t;
	};
	_proto.RipeTime8_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime8 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 314;
		t.y = 533.5799999999999;
		return t;
	};
	_proto.RipeTime9_i = function () {
		var t = new eui.ProgressBar();
		this.RipeTime9 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.visible = false;
		t.width = 200;
		t.x = 324;
		t.y = 543.5799999999999;
		return t;
	};
	_proto.manure1_i = function () {
		var t = new eui.Button();
		this.manure1 = t;
		t.height = 50;
		t.label = "0";
		t.visible = false;
		t.width = 50;
		t.x = 97;
		t.y = 365;
		t.skinName = TheFarmLobbySkin$Skin15;
		return t;
	};
	_proto.manure2_i = function () {
		var t = new eui.Button();
		this.manure2 = t;
		t.height = 50;
		t.label = "0";
		t.visible = false;
		t.width = 50;
		t.x = 327;
		t.y = 339;
		t.skinName = TheFarmLobbySkin$Skin16;
		return t;
	};
	_proto.manure3_i = function () {
		var t = new eui.Button();
		this.manure3 = t;
		t.height = 50;
		t.label = "0";
		t.visible = false;
		t.width = 50;
		t.x = 192;
		t.y = 467;
		t.skinName = TheFarmLobbySkin$Skin17;
		return t;
	};
	_proto.AggregateScheduling1_i = function () {
		var t = new eui.Image();
		this.AggregateScheduling1 = t;
		t.height = 100;
		t.source = "header_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 300;
		return t;
	};
	_proto.Scheduling1_i = function () {
		var t = new eui.Image();
		this.Scheduling1 = t;
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 300;
		t.y = 300;
		return t;
	};
	_proto.Scheduling_Number1_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number1 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 310;
		t.y = 310;
		return t;
	};
	_proto.Scheduling_Number9_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number9 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 320;
		t.y = 320;
		return t;
	};
	_proto.Scheduling_Number2_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number2 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 330;
		t.y = 330;
		return t;
	};
	_proto.Scheduling_Number3_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number3 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 340;
		t.y = 340;
		return t;
	};
	_proto.Scheduling_Number4_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number4 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 350;
		t.y = 350;
		return t;
	};
	_proto.Scheduling_Number5_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number5 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 360;
		t.y = 360;
		return t;
	};
	_proto.Scheduling_Number6_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number6 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 370;
		t.y = 370;
		return t;
	};
	_proto.Scheduling_Number7_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number7 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
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
		t.height = 100;
		t.source = "white2_png";
		t.visible = false;
		t.width = 100;
		t.x = 380;
		t.y = 380;
		return t;
	};
	_proto.Scheduling_Number8_i = function () {
		var t = new eui.Label();
		this.Scheduling_Number8 = t;
		t.size = 27;
		t.text = "Label";
		t.textColor = 0x000000;
		t.visible = false;
		t.x = 393;
		t.y = 415;
		return t;
	};
	_proto.Options_i = function () {
		var t = new eui.Group();
		this.Options = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image7_i(),this._Image8_i(),this.Music_i(),this._Label1_i(),this._Label2_i(),this.SoundEffect_i(),this._Image9_i(),this._Image10_i(),this.Options_close_i()];
		return t;
	};
	_proto._Image7_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image8_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 479.58;
		t.source = "set2_png";
		t.width = 372.73;
		t.x = 59;
		t.y = 290;
		return t;
	};
	_proto.Music_i = function () {
		var t = new eui.HSlider();
		this.Music = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.maximum = 80;
		t.width = 200;
		t.x = 180;
		t.y = 480;
		return t;
	};
	_proto._Label1_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.text = "音乐";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 92;
		t.x = 70;
		t.y = 480;
		return t;
	};
	_proto._Label2_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.text = "音效";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 92;
		t.x = 70;
		t.y = 560;
		return t;
	};
	_proto.SoundEffect_i = function () {
		var t = new eui.HSlider();
		this.SoundEffect = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.width = 200;
		t.x = 180;
		t.y = 566.06;
		return t;
	};
	_proto._Image9_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 49.78;
		t.source = "jhda_png";
		t.width = 218.45;
		t.x = 127.64;
		t.y = 288.47;
		return t;
	};
	_proto._Image10_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 37.49;
		t.source = "set_png";
		t.width = 62.67;
		t.x = 214.18;
		t.y = 293.76;
		return t;
	};
	_proto.Options_close_i = function () {
		var t = new eui.Button();
		this.Options_close = t;
		t.anchorOffsetX = 50;
		t.anchorOffsetY = 50;
		t.height = 70;
		t.label = "";
		t.width = 70;
		t.x = 389.06;
		t.y = 326.69;
		t.skinName = TheFarmLobbySkin$Skin18;
		return t;
	};
	_proto.MyCenter_i = function () {
		var t = new eui.Group();
		this.MyCenter = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image11_i(),this._Image12_i(),this._Image13_i(),this.MyCenter_close_i(),this.ChangeName_i(),this.modification_i(),this._Image14_i(),this._Image15_i(),this.CurrentTitleLevel_i(),this.detail_i(),this.TheCurrentMoney_i(),this.TotalMoney_i(),this._Label3_i(),this.TheNextLevel_i(),this.TotalMoneyTow_i(),this.TheNextLevelMoney_i(),this._Label4_i(),this._Label5_i(),this._Label6_i(),this._Label7_i(),this._Label8_i(),this.fertilizer_i(),this._Button2_i(),this.plan_i(),this.schedule_i(),this._Image16_i(),this._Image17_i(),this._Image18_i(),this._Image19_i(),this._Image20_i(),this._Image21_i(),this.Money_center_i()];
		return t;
	};
	_proto._Image11_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -0.94;
		t.y = 0;
		return t;
	};
	_proto._Image12_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 958;
		t.source = "beijing3_png";
		t.width = 480;
		t.x = 8;
		t.y = 22;
		return t;
	};
	_proto._Image13_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 64;
		t.source = "juxing3_png";
		t.visible = false;
		t.width = 536;
		t.x = 32.06;
		t.y = 239.35;
		return t;
	};
	_proto.MyCenter_close_i = function () {
		var t = new eui.Button();
		this.MyCenter_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 412.06;
		t.y = 3.64;
		t.skinName = TheFarmLobbySkin$Skin19;
		return t;
	};
	_proto.ChangeName_i = function () {
		var t = new eui.TextInput();
		this.ChangeName = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.maxChars = 5;
		t.width = 270;
		t.x = 120.72;
		t.y = 213;
		return t;
	};
	_proto.modification_i = function () {
		var t = new eui.Button();
		this.modification = t;
		t.enabled = false;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 399;
		t.y = 202;
		t.skinName = TheFarmLobbySkin$Skin20;
		return t;
	};
	_proto._Image14_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 306;
		t.source = "juxing3_png";
		t.width = 422;
		t.x = 30;
		t.y = 616;
		return t;
	};
	_proto._Image15_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 327.33;
		t.source = "juxing3_png";
		t.width = 412.66;
		t.x = 41.67;
		t.y = 277;
		return t;
	};
	_proto.CurrentTitleLevel_i = function () {
		var t = new eui.Image();
		this.CurrentTitleLevel = t;
		t.height = 30;
		t.source = "zt1_png";
		t.width = 80;
		t.x = 302;
		t.y = 294;
		return t;
	};
	_proto.detail_i = function () {
		var t = new eui.Button();
		this.detail = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.label = "";
		t.width = 50;
		t.x = 566;
		t.y = 336;
		t.skinName = TheFarmLobbySkin$Skin21;
		return t;
	};
	_proto.TheCurrentMoney_i = function () {
		var t = new eui.Label();
		this.TheCurrentMoney = t;
		t.fontFamily = "SimSun";
		t.text = "Label";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.x = 230;
		t.y = 346;
		return t;
	};
	_proto.TotalMoney_i = function () {
		var t = new eui.Label();
		this.TotalMoney = t;
		t.fontFamily = "SimSun";
		t.text = "0";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.x = 235;
		t.y = 394;
		return t;
	};
	_proto._Label3_i = function () {
		var t = new eui.Label();
		t.text = "下一等级为：";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.x = 58;
		t.y = 444;
		return t;
	};
	_proto.TheNextLevel_i = function () {
		var t = new eui.Label();
		this.TheNextLevel = t;
		t.text = "中农";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.x = 232;
		t.y = 444;
		return t;
	};
	_proto.TotalMoneyTow_i = function () {
		var t = new eui.Label();
		this.TotalMoneyTow = t;
		t.height = 30;
		t.text = "0";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "justify";
		t.width = 120;
		t.x = 10;
		t.y = 554;
		return t;
	};
	_proto.TheNextLevelMoney_i = function () {
		var t = new eui.Label();
		this.TheNextLevelMoney = t;
		t.height = 30;
		t.text = "99";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 120;
		t.x = 350;
		t.y = 554;
		return t;
	};
	_proto._Label4_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.fontFamily = "SimSun";
		t.height = 116;
		t.size = 27;
		t.text = "获得更多金钱的方法：";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.width = 346;
		t.x = 42;
		t.y = 622;
		return t;
	};
	_proto._Label5_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 116;
		t.size = 24;
		t.text = "1.点击道具购买肥料加大产量。";
		t.textColor = 0x000000;
		t.width = 364;
		t.x = 42;
		t.y = 672;
		return t;
	};
	_proto._Label6_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 116;
		t.size = 24;
		t.text = "2.进好友庄园偷取好友的金钱。";
		t.textColor = 0x000000;
		t.width = 370;
		t.x = 42;
		t.y = 754;
		return t;
	};
	_proto._Label7_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.fontFamily = "SimSun";
		t.height = 116;
		t.size = 24;
		t.text = "3.评价商城的购物订单。";
		t.textColor = 0x000000;
		t.width = 276;
		t.x = 42;
		t.y = 828;
		return t;
	};
	_proto._Label8_i = function () {
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
		t.label = "";
		t.x = 376;
		t.y = 668;
		t.skinName = TheFarmLobbySkin$Skin22;
		return t;
	};
	_proto._Button2_i = function () {
		var t = new eui.Button();
		t.label = "";
		t.visible = false;
		t.x = 376;
		t.y = 838;
		t.skinName = TheFarmLobbySkin$Skin23;
		return t;
	};
	_proto.plan_i = function () {
		var t = new eui.Image();
		this.plan = t;
		t.height = 50;
		t.source = "juxing(2)_png";
		t.width = 360;
		t.x = 68;
		t.y = 494;
		return t;
	};
	_proto.schedule_i = function () {
		var t = new eui.Image();
		this.schedule = t;
		t.height = 50;
		t.source = "jixing8_png";
		t.width = 0;
		t.x = 100;
		t.y = 540;
		return t;
	};
	_proto._Image16_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 33;
		t.source = "leijihulubi_png";
		t.width = 128;
		t.x = 58;
		t.y = 394.02;
		return t;
	};
	_proto._Image17_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 44.24;
		t.source = "dengji_png";
		t.width = 220;
		t.x = 58;
		t.y = 284;
		return t;
	};
	_proto._Image18_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "dangqianhulubi_png";
		t.width = 152;
		t.x = 58;
		t.y = 344;
		return t;
	};
	_proto._Image19_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "name_png";
		t.width = 80;
		t.x = 34;
		t.y = 214;
		return t;
	};
	_proto._Image20_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "beijing@2x(8)_png";
		t.width = 270;
		t.x = 104;
		t.y = 104;
		return t;
	};
	_proto._Image21_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.source = "hulu_png";
		t.width = 40;
		t.x = 144;
		t.y = 148;
		return t;
	};
	_proto.Money_center_i = function () {
		var t = new eui.Label();
		this.Money_center = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.size = 30;
		t.text = "Label";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 120;
		t.x = 215.39;
		t.y = 146;
		return t;
	};
	_proto.Designation_i = function () {
		var t = new eui.Group();
		this.Designation = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image22_i(),this._Image23_i(),this.Designation_close_i(),this._Image24_i(),this._Image25_i(),this._Image26_i(),this._Image27_i(),this._Image28_i(),this._Image29_i(),this._Image30_i(),this._Image31_i(),this._Image32_i(),this._Label9_i(),this._Label10_i(),this._Label11_i(),this._Label12_i(),this._Label13_i(),this._Label14_i(),this._Label15_i(),this._Label16_i(),this._Label17_i(),this._Image33_i(),this._Image34_i(),this._Image35_i(),this._Button3_i()];
		return t;
	};
	_proto._Image22_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image23_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 857.15;
		t.source = "bj_png";
		t.width = 466.79;
		t.x = 17;
		t.y = 90;
		return t;
	};
	_proto.Designation_close_i = function () {
		var t = new eui.Button();
		this.Designation_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 379.45;
		t.y = 114;
		t.skinName = TheFarmLobbySkin$Skin24;
		return t;
	};
	_proto._Image24_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "dianhu3x_png";
		t.width = 140;
		t.x = 56;
		t.y = 295;
		return t;
	};
	_proto._Image25_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "pinmin3x_png";
		t.width = 140;
		t.x = 54;
		t.y = 346;
		return t;
	};
	_proto._Image26_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "zhongnong3x_png";
		t.width = 140;
		t.x = 54;
		t.y = 399;
		return t;
	};
	_proto._Image27_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "funong3x_png";
		t.width = 140;
		t.x = 54;
		t.y = 451;
		return t;
	};
	_proto._Image28_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "dizhu3x_png";
		t.width = 140;
		t.x = 54;
		t.y = 501;
		return t;
	};
	_proto._Image29_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "fuwong3x_png";
		t.width = 140;
		t.x = 56;
		t.y = 549;
		return t;
	};
	_proto._Image30_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "zhuangzhu3x_png";
		t.width = 140;
		t.x = 56;
		t.y = 599;
		return t;
	};
	_proto._Image31_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "lingzhu3x_png";
		t.width = 140;
		t.x = 56;
		t.y = 652;
		return t;
	};
	_proto._Image32_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "qiuzhang3x_png";
		t.width = 140;
		t.x = 56;
		t.y = 701;
		return t;
	};
	_proto._Label9_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "非代言人";
		t.textColor = 0x000000;
		t.width = 158;
		t.x = 228;
		t.y = 301;
		return t;
	};
	_proto._Label10_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "30000-99999";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 212;
		t.x = 228;
		t.y = 601;
		return t;
	};
	_proto._Label11_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "100000-299999";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 224;
		t.x = 228;
		t.y = 651;
		return t;
	};
	_proto._Label12_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "300000以上";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 216;
		t.x = 228;
		t.y = 699;
		return t;
	};
	_proto._Label13_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "0-99";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 106;
		t.x = 228;
		t.y = 349;
		return t;
	};
	_proto._Label14_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "100-299";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 164;
		t.x = 228;
		t.y = 397;
		return t;
	};
	_proto._Label15_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "300-999";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 161;
		t.x = 228;
		t.y = 447;
		return t;
	};
	_proto._Label16_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "1000-4999";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 203;
		t.x = 228;
		t.y = 497;
		return t;
	};
	_proto._Label17_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 50;
		t.text = "5000-29999";
		t.textAlign = "left";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 220;
		t.x = 228;
		t.y = 547;
		return t;
	};
	_proto._Image33_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 64;
		t.source = "chenghao_png";
		t.width = 206;
		t.x = 146;
		t.y = 116;
		return t;
	};
	_proto._Image34_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48;
		t.source = "称号@3x_png";
		t.width = 120;
		t.x = 62;
		t.y = 220;
		return t;
	};
	_proto._Image35_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.source = "累计葫芦币@3x_png";
		t.width = 174;
		t.x = 224;
		t.y = 218;
		return t;
	};
	_proto._Button3_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 66;
		t.label = "确定";
		t.width = 192;
		t.x = 147;
		t.y = 804;
		t.skinName = TheFarmLobbySkin$Skin25;
		return t;
	};
	_proto.TheIndianArrowheads_i = function () {
		var t = new eui.Group();
		this.TheIndianArrowheads = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image36_i(),this._Image37_i(),this.TheIndianArrowheads_close_i(),this._Image38_i(),this.Goldhoe_i(),this._Image39_i(),this._Button4_i(),this._Button5_i(),this._Image40_i(),this.IndianArrowheadsNumber_i(),this._Image41_i(),this.Indian_arrowheads_record1_i(),this._Image42_i(),this._Image43_i(),this._Button6_i(),this.inform_i(),this.Indian_arrowheads_record2_i()];
		return t;
	};
	_proto._Image36_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image37_i = function () {
		var t = new eui.Image();
		t.height = 860;
		t.source = "beijing3_png";
		t.width = 460;
		t.x = 16;
		t.y = 76;
		return t;
	};
	_proto.TheIndianArrowheads_close_i = function () {
		var t = new eui.Button();
		this.TheIndianArrowheads_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 386.06;
		t.y = 68;
		t.skinName = TheFarmLobbySkin$Skin26;
		return t;
	};
	_proto._Image38_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 80;
		t.source = "wabao2_png";
		t.width = 300;
		t.x = 94;
		t.y = 68;
		return t;
	};
	_proto.Goldhoe_i = function () {
		var t = new eui.Button();
		this.Goldhoe = t;
		t.anchorOffsetX = 0;
		t.height = 150;
		t.label = "";
		t.width = 140;
		t.x = 30;
		t.y = 342;
		t.skinName = TheFarmLobbySkin$Skin27;
		return t;
	};
	_proto._Image39_i = function () {
		var t = new eui.Image();
		t.height = 60;
		t.source = "juxing@3x(1)_png";
		t.width = 100;
		t.x = 285;
		t.y = 764.51;
		return t;
	};
	_proto._Button4_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.height = 150;
		t.label = "";
		t.width = 140;
		t.x = 172;
		t.y = 342;
		t.skinName = TheFarmLobbySkin$Skin28;
		return t;
	};
	_proto._Button5_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.height = 150;
		t.label = "";
		t.width = 140;
		t.x = 314.49;
		t.y = 342;
		t.skinName = TheFarmLobbySkin$Skin29;
		return t;
	};
	_proto._Image40_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 260;
		t.source = "baoxiang@2x_png";
		t.width = 360;
		t.x = 66.24;
		t.y = 508;
		return t;
	};
	_proto.IndianArrowheadsNumber_i = function () {
		var t = new eui.Label();
		this.IndianArrowheadsNumber = t;
		t.height = 40;
		t.text = "1";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 80;
		t.x = 292.67;
		t.y = 772;
		return t;
	};
	_proto._Image41_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 6.84;
		t.source = "fenge@2x_png";
		t.width = 414;
		t.x = 36.94;
		t.y = 821.67;
		return t;
	};
	_proto.Indian_arrowheads_record1_i = function () {
		var t = new eui.Button();
		this.Indian_arrowheads_record1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 350;
		t.y = 169.39;
		t.skinName = TheFarmLobbySkin$Skin30;
		return t;
	};
	_proto._Image42_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 44.24;
		t.source = "wenzi@3x_png";
		t.width = 244.24;
		t.x = 40.23;
		t.y = 772.49;
		return t;
	};
	_proto._Image43_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.source = "kaishi@3x_png";
		t.width = 180;
		t.x = 165.36;
		t.y = 279.5;
		return t;
	};
	_proto._Button6_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.label = "成为代言人 每天可增加挖宝次数";
		t.width = 387.88;
		t.x = 55;
		t.y = 836;
		return t;
	};
	_proto.inform_i = function () {
		var t = new eui.Group();
		this.inform = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 400;
		t.visible = false;
		t.width = 540;
		t.x = 93;
		t.y = 284;
		t.elementsContent = [this._Image44_i(),this._Image45_i(),this.inform_frame_i(),this.inform_close_i(),this._Image46_i()];
		return t;
	};
	_proto._Image44_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -93;
		t.y = -285.33;
		return t;
	};
	_proto._Image45_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 400;
		t.source = "zu2_png";
		t.width = 540;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.inform_frame_i = function () {
		var t = new eui.Label();
		this.inform_frame = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 228.66;
		t.text = "对不起您还没有购买白金锄头，请到道具商店购买后使用。";
		t.textColor = 0x000000;
		t.width = 446;
		t.x = 47;
		t.y = 56;
		return t;
	};
	_proto.inform_close_i = function () {
		var t = new eui.Button();
		this.inform_close = t;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 218;
		t.y = 307;
		t.skinName = TheFarmLobbySkin$Skin31;
		return t;
	};
	_proto._Image46_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 121;
		t.source = "jilu@2x_png";
		t.width = 285;
		t.x = 120.5;
		t.y = -59.88;
		return t;
	};
	_proto.Indian_arrowheads_record2_i = function () {
		var t = new eui.Group();
		this.Indian_arrowheads_record2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 858;
		t.visible = false;
		t.width = 596;
		t.x = 64;
		t.y = 122;
		t.elementsContent = [this._Image47_i(),this._Image48_i(),this._Scroller1_i(),this.Indian_arrowheads_record2_close_i()];
		return t;
	};
	_proto._Image47_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "zu2_png";
		t.top = 0;
		t.x = -64;
		t.y = -122;
		return t;
	};
	_proto._Image48_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 96;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "jilu@2x_png";
		t.width = 354;
		t.x = 128.33;
		t.y = -44;
		return t;
	};
	_proto._Scroller1_i = function () {
		var t = new eui.Scroller();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 732;
		t.width = 540;
		t.x = 26;
		t.y = 86;
		t.viewport = this.Log_message_i();
		return t;
	};
	_proto.Log_message_i = function () {
		var t = new eui.Group();
		this.Log_message = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 268;
		t.width = 282;
		return t;
	};
	_proto.Indian_arrowheads_record2_close_i = function () {
		var t = new eui.Button();
		this.Indian_arrowheads_record2_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 520;
		t.skinName = TheFarmLobbySkin$Skin32;
		return t;
	};
	_proto.ForRecord_i = function () {
		var t = new eui.Group();
		this.ForRecord = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image49_i(),this._Image50_i(),this.ForRecord_close_i(),this._Image51_i(),this._Label18_i(),this.money_i(),this._Label19_i(),this._Image52_i(),this._Image53_i(),this._Image54_i(),this._Label20_i(),this.GoAndSee_i(),this.RecordStrip_i()];
		return t;
	};
	_proto._Image49_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image50_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.height = 880;
		t.source = "beijing3_png";
		t.width = 444;
		t.x = 28;
		t.y = 72;
		return t;
	};
	_proto.ForRecord_close_i = function () {
		var t = new eui.Button();
		this.ForRecord_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 383;
		t.y = 74.6;
		t.skinName = TheFarmLobbySkin$Skin33;
		return t;
	};
	_proto._Image51_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 76;
		t.source = "z3_png";
		t.width = 244;
		t.x = 128;
		t.y = 72;
		return t;
	};
	_proto._Label18_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 30;
		t.rotation = 0.02;
		t.text = "当前金钱：";
		t.textColor = 0x000000;
		t.width = 157.34;
		t.x = 65;
		t.y = 708.03;
		return t;
	};
	_proto.money_i = function () {
		var t = new eui.Label();
		this.money = t;
		t.text = "Label";
		t.textColor = 0x000000;
		t.x = 208.91;
		t.y = 708.03;
		return t;
	};
	_proto._Label19_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 39.09;
		t.text = "金钱可用于：";
		t.textColor = 0x000000;
		t.width = 230.06;
		t.x = 59;
		t.y = 788;
		return t;
	};
	_proto._Image52_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 38.18;
		t.source = "duihuan@3x_png";
		t.width = 100;
		t.x = 66;
		t.y = 853;
		return t;
	};
	_proto._Image53_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 38.18;
		t.source = "xianshang@3x_png";
		t.width = 100;
		t.x = 192;
		t.y = 853;
		return t;
	};
	_proto._Image54_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 38.18;
		t.source = "xianxia@3x_png";
		t.width = 100;
		t.x = 318;
		t.y = 853;
		return t;
	};
	_proto._Label20_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 43.64;
		t.text = "获得更多金钱的方法：";
		t.textColor = 0x000000;
		t.width = 316.43;
		t.x = 50;
		t.y = 748;
		return t;
	};
	_proto.GoAndSee_i = function () {
		var t = new eui.Button();
		this.GoAndSee = t;
		t.label = "去看看";
		t.x = 345;
		t.y = 739;
		return t;
	};
	_proto.RecordStrip_i = function () {
		var t = new eui.Scroller();
		this.RecordStrip = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 500;
		t.width = 382;
		t.x = 58;
		t.y = 184;
		t.viewport = this.RecordBox_i();
		return t;
	};
	_proto.RecordBox_i = function () {
		var t = new eui.Group();
		this.RecordBox = t;
		t.anchorOffsetX = 0;
		t.height = 500;
		t.width = 320;
		t.elementsContent = [this.record_BJ_i(),this.record_i(),this.record_Time_i()];
		return t;
	};
	_proto.record_BJ_i = function () {
		var t = new eui.Image();
		this.record_BJ = t;
		t.height = 100;
		t.source = "turquoise_png";
		t.visible = false;
		t.width = 420;
		t.x = 30;
		t.y = 0;
		return t;
	};
	_proto.record_i = function () {
		var t = new eui.Label();
		this.record = t;
		t.text = "记录";
		t.visible = false;
		t.x = 56;
		t.y = 10;
		return t;
	};
	_proto.record_Time_i = function () {
		var t = new eui.Label();
		this.record_Time = t;
		t.text = "时间";
		t.visible = false;
		t.x = 59;
		t.y = 57;
		return t;
	};
	_proto.BrowserHelpMenu_i = function () {
		var t = new eui.Group();
		this.BrowserHelpMenu = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image55_i(),this._Image56_i(),this.BrowserHelpMenu_close_i(),this._Image57_i(),this.HelpText_i(),this.HelpText0_i(),this.HelpText1_i(),this.HelpText2_i(),this.RaiseMoney_i(),this.Water_i(),this.Seed_i(),this.PetPig_i()];
		return t;
	};
	_proto._Image55_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image56_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 860;
		t.source = "beijing3_png";
		t.width = 460;
		t.x = 20;
		t.y = 98;
		return t;
	};
	_proto.BrowserHelpMenu_close_i = function () {
		var t = new eui.Button();
		this.BrowserHelpMenu_close = t;
		t.anchorOffsetX = 0;
		t.height = 80;
		t.label = "";
		t.width = -60;
		t.x = 380.06;
		t.y = 102;
		t.skinName = TheFarmLobbySkin$Skin34;
		return t;
	};
	_proto._Image57_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 62;
		t.source = "banghzu_png";
		t.width = 246;
		t.x = 130;
		t.y = 111.39;
		return t;
	};
	_proto.HelpText_i = function () {
		var t = new eui.Label();
		this.HelpText = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 530;
		t.text = "无肥料的情况下，XX每24小时产出10XX,购买肥料可使XX翻倍";
		t.textColor = 0x000000;
		t.width = 377.94;
		t.x = 60;
		t.y = 322;
		return t;
	};
	_proto.HelpText0_i = function () {
		var t = new eui.Label();
		this.HelpText0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 530;
		t.text = "浇水可使自己的作物增加产量，并减少成熟时间";
		t.textColor = 0x000000;
		t.visible = false;
		t.width = 517.94;
		t.x = 100;
		t.y = 400;
		return t;
	};
	_proto.HelpText1_i = function () {
		var t = new eui.Label();
		this.HelpText1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 530;
		t.text = "1.种子在用户购买后在自己庄园种植或赠送给好友。                                       2.耕种期间，用户需在庄园内进行日常打理（浇水、除虫）来帮助作物生长。";
		t.textColor = 0x000000;
		t.visible = false;
		t.width = 517.94;
		t.x = 100;
		t.y = 400;
		return t;
	};
	_proto.HelpText2_i = function () {
		var t = new eui.Label();
		this.HelpText2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 530;
		t.text = "无肥料的情况下，XX每24小时产出10XX,购买肥料可使XX翻倍";
		t.textColor = 0x000000;
		t.visible = false;
		t.width = 517.94;
		t.x = 100;
		t.y = 400;
		return t;
	};
	_proto.RaiseMoney_i = function () {
		var t = new eui.Image();
		this.RaiseMoney = t;
		t.anchorOffsetX = 0;
		t.height = 50;
		t.source = "hulu@3x0_png";
		t.width = 100;
		t.x = 40;
		t.y = 222;
		return t;
	};
	_proto.Water_i = function () {
		var t = new eui.Image();
		this.Water = t;
		t.anchorOffsetX = 0;
		t.height = 50;
		t.source = "jiaoshui@3x0_png";
		t.width = 100;
		t.x = 145;
		t.y = 222;
		return t;
	};
	_proto.Seed_i = function () {
		var t = new eui.Image();
		this.Seed = t;
		t.anchorOffsetX = 0;
		t.height = 50;
		t.source = "zhongzi@3x0_png";
		t.width = 100;
		t.x = 252;
		t.y = 222;
		return t;
	};
	_proto.PetPig_i = function () {
		var t = new eui.Image();
		this.PetPig = t;
		t.anchorOffsetX = 0;
		t.height = 50;
		t.source = "chongwu@3x0_png";
		t.width = 100;
		t.x = 358;
		t.y = 222;
		return t;
	};
	_proto.commodity_i = function () {
		var t = new eui.Group();
		this.commodity = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = -4.56;
		t.y = 0;
		t.elementsContent = [this._Image58_i(),this._Image59_i(),this.PropsFor_close_i(),this._Image60_i(),this._Image61_i(),this.ContentProps_i(),this.Conversion_i(),this.PropsClass_i(),this.ConversionGoods_i()];
		return t;
	};
	_proto._Image58_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 3.04;
		t.y = 0;
		return t;
	};
	_proto._Image59_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 930;
		t.source = "beijing3_png";
		t.width = 434;
		t.x = 40;
		t.y = 40;
		return t;
	};
	_proto.PropsFor_close_i = function () {
		var t = new eui.Button();
		this.PropsFor_close = t;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 400.73;
		t.y = 51.37;
		t.skinName = TheFarmLobbySkin$Skin35;
		return t;
	};
	_proto._Image60_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 102.12;
		t.source = "daojuduihuan_png";
		t.width = 309.39;
		t.x = 94;
		t.y = 32;
		return t;
	};
	_proto._Image61_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 70.31;
		t.source = "fenge2_png";
		t.width = 362.85;
		t.x = 75;
		t.y = 211.2;
		return t;
	};
	_proto.ContentProps_i = function () {
		var t = new eui.Image();
		this.ContentProps = t;
		t.height = 60;
		t.source = "daoju_png";
		t.width = 150;
		t.x = 104;
		t.y = 144;
		return t;
	};
	_proto.Conversion_i = function () {
		var t = new eui.Image();
		this.Conversion = t;
		t.height = 60;
		t.source = "duihuan_png";
		t.width = 150;
		t.x = 255.56;
		t.y = 144;
		return t;
	};
	_proto.PropsClass_i = function () {
		var t = new eui.Group();
		this.PropsClass = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 643.33;
		t.width = 366.67;
		t.x = 66;
		t.y = 262;
		t.elementsContent = [this._Image62_i(),this.prop_money_i(),this.PropsRecord_i(),this._Image63_i(),this.consume_i(),this.function_i(),this.seed_i(),this.petattack_i(),this.warehouse_i(),this.ConsumeGoods_i(),this.WarehouseGoods_i(),this.ConsumeGoods_Buy_i(),this.PropsOrder_i()];
		return t;
	};
	_proto._Image62_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 33;
		t.source = "jux@2x(2)_png";
		t.width = 100;
		t.x = 79.32;
		t.y = -51.99;
		return t;
	};
	_proto.prop_money_i = function () {
		var t = new eui.Label();
		this.prop_money = t;
		t.height = 33;
		t.size = 24;
		t.text = "钱数";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 100;
		t.x = 79.32;
		t.y = -49.47;
		return t;
	};
	_proto.PropsRecord_i = function () {
		var t = new eui.Button();
		this.PropsRecord = t;
		t.height = 33;
		t.label = "";
		t.width = 100;
		t.x = 214.01;
		t.y = -54.66;
		t.skinName = TheFarmLobbySkin$Skin36;
		return t;
	};
	_proto._Image63_i = function () {
		var t = new eui.Image();
		t.height = 30;
		t.source = "hulu_png";
		t.width = 20;
		t.x = 58.33;
		t.y = -51.99;
		return t;
	};
	_proto.consume_i = function () {
		var t = new eui.Image();
		this.consume = t;
		t.height = 35;
		t.source = "xiaohao_png";
		t.width = 70;
		t.x = 2.96;
		t.y = 34.53;
		return t;
	};
	_proto.function_i = function () {
		var t = new eui.Image();
		this.function = t;
		t.height = 35;
		t.source = "gongneng_png";
		t.width = 70;
		t.x = 78.01;
		t.y = 34.53;
		return t;
	};
	_proto.seed_i = function () {
		var t = new eui.Image();
		this.seed = t;
		t.height = 35;
		t.source = "zhongzi@3x_png";
		t.width = 70;
		t.x = 155.31;
		t.y = 34.53;
		return t;
	};
	_proto.petattack_i = function () {
		var t = new eui.Image();
		this.petattack = t;
		t.height = 35;
		t.source = "chongwu@2x_png";
		t.width = 70;
		t.x = 231.91;
		t.y = 34.53;
		return t;
	};
	_proto.warehouse_i = function () {
		var t = new eui.Image();
		this.warehouse = t;
		t.height = 35;
		t.source = "cangku@2x_png";
		t.width = 70;
		t.x = 306.98;
		t.y = 35.43;
		return t;
	};
	_proto.ConsumeGoods_i = function () {
		var t = new eui.Group();
		this.ConsumeGoods = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 560;
		t.width = 333;
		t.x = 14;
		t.y = 80;
		return t;
	};
	_proto.WarehouseGoods_i = function () {
		var t = new eui.Group();
		this.WarehouseGoods = t;
		t.height = 560;
		t.visible = false;
		t.width = 333;
		t.x = 14;
		t.y = 80;
		t.elementsContent = [this.cry_i()];
		return t;
	};
	_proto.cry_i = function () {
		var t = new eui.Group();
		this.cry = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 198.48;
		t.width = 307.58;
		t.x = 11.82;
		t.y = 120;
		t.elementsContent = [this._Image64_i(),this._Label21_i()];
		return t;
	};
	_proto._Image64_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 106.06;
		t.source = "mouse_png";
		t.width = 106.55;
		t.x = 11.41;
		t.y = 56.08;
		return t;
	};
	_proto._Label21_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 36.06;
		t.size = 24;
		t.text = "背包好空啊(;´༎ຶД༎ຶ`)";
		t.textColor = 0x000000;
		t.width = 121.36;
		t.x = 143.33;
		t.y = 84.08;
		return t;
	};
	_proto.ConsumeGoods_Buy_i = function () {
		var t = new eui.Group();
		this.ConsumeGoods_Buy = t;
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.visible = false;
		t.width = 500;
		t.x = -51.36;
		t.y = -260;
		t.elementsContent = [this._Image65_i(),this._Image66_i(),this._Image67_i(),this.ConsumeGoods_close_i(),this._Image68_i(),this._Image69_i(),this.buy_text_i(),this.ConsumeGoods_Subtract_i(),this.ConsumeGoods_Plus_i(),this.PurchaseQuantity_i(),this._Button7_i(),this.purchase0_i(),this._Image70_i(),this._Image71_i(),this._Image72_i(),this.price_i()];
		return t;
	};
	_proto._Image65_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -15;
		return t;
	};
	_proto._Image66_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 575.76;
		t.source = "qwe3x_png";
		t.width = 390;
		t.x = 43.28;
		t.y = 204.28;
		return t;
	};
	_proto._Image67_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 129;
		t.source = "juxing3_png";
		t.width = 313.79;
		t.x = 80.97;
		t.y = 311.28;
		return t;
	};
	_proto.ConsumeGoods_close_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_close = t;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 374.04;
		t.y = 201.76;
		t.skinName = TheFarmLobbySkin$Skin37;
		return t;
	};
	_proto._Image68_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 120;
		t.source = "feiliao_png";
		t.width = 120;
		t.x = 78.61;
		t.y = 516.22;
		return t;
	};
	_proto._Image69_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "juxing(1)_png";
		t.width = 120;
		t.x = 244.21;
		t.y = 551.38;
		return t;
	};
	_proto.buy_text_i = function () {
		var t = new eui.Label();
		this.buy_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.background = false;
		t.backgroundColor = 0xf2e6e6;
		t.height = 120;
		t.multiline = true;
		t.text = "增加";
		t.textColor = 0x000000;
		t.width = 294.09;
		t.x = 94.61;
		t.y = 321.8;
		return t;
	};
	_proto.ConsumeGoods_Subtract_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Subtract = t;
		t.height = 50;
		t.label = "";
		t.width = 50;
		t.x = 199.21;
		t.y = 551.38;
		t.skinName = TheFarmLobbySkin$Skin38;
		return t;
	};
	_proto.ConsumeGoods_Plus_i = function () {
		var t = new eui.Button();
		this.ConsumeGoods_Plus = t;
		t.height = 50;
		t.label = "";
		t.width = 50;
		t.x = 362.99;
		t.y = 552.91;
		t.skinName = TheFarmLobbySkin$Skin39;
		return t;
	};
	_proto.PurchaseQuantity_i = function () {
		var t = new eui.Label();
		this.PurchaseQuantity = t;
		t.text = "1";
		t.x = 295.71;
		t.y = 561.38;
		return t;
	};
	_proto._Button7_i = function () {
		var t = new eui.Button();
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 90;
		t.y = 654;
		t.skinName = TheFarmLobbySkin$Skin40;
		return t;
	};
	_proto.purchase0_i = function () {
		var t = new eui.Button();
		this.purchase0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 290;
		t.y = 654;
		t.skinName = TheFarmLobbySkin$Skin41;
		return t;
	};
	_proto._Image70_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75.76;
		t.source = "goumai_png";
		t.width = 308.97;
		t.x = 75.36;
		t.y = 187.76;
		return t;
	};
	_proto._Image71_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 25;
		t.source = "xianjinfuli_png";
		t.width = 40;
		t.x = 276.84;
		t.y = 616.13;
		return t;
	};
	_proto._Image72_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 34;
		t.source = "jiazhi@3x_png";
		t.width = 60;
		t.x = 202.48;
		t.y = 611.41;
		return t;
	};
	_proto.price_i = function () {
		var t = new eui.Label();
		this.price = t;
		t.text = "0元";
		t.textAlign = "center";
		t.textColor = 0x000000;
		t.verticalAlign = "middle";
		t.width = 70;
		t.x = 333.57;
		t.y = 615.45;
		return t;
	};
	_proto.PropsOrder_i = function () {
		var t = new eui.Group();
		this.PropsOrder = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = -55.44;
		t.y = -262.4;
		t.elementsContent = [this._Image73_i(),this._Image74_i(),this.PropsOrder_close_i(),this._Image75_i(),this.Indent_i()];
		return t;
	};
	_proto._Image73_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -10;
		t.y = 0;
		return t;
	};
	_proto._Image74_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.height = 800;
		t.source = "qwe3x_png";
		t.width = 447.27;
		t.x = 27.35;
		t.y = 125.73;
		return t;
	};
	_proto.PropsOrder_close_i = function () {
		var t = new eui.Button();
		this.PropsOrder_close = t;
		t.height = 60;
		t.label = "";
		t.width = 60;
		t.x = 411.44;
		t.y = 130.4;
		t.skinName = TheFarmLobbySkin$Skin42;
		return t;
	};
	_proto._Image75_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 90;
		t.source = "daojudingdan_png";
		t.width = 333;
		t.x = 82.41;
		t.y = 115.03;
		return t;
	};
	_proto.Indent_i = function () {
		var t = new eui.Scroller();
		this.Indent = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 660;
		t.width = 388.85;
		t.x = 50.16;
		t.y = 221.76;
		t.viewport = this.OrderForm_i();
		return t;
	};
	_proto.OrderForm_i = function () {
		var t = new eui.Group();
		this.OrderForm = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 106.06;
		t.width = 476.73;
		return t;
	};
	_proto.ConversionGoods_i = function () {
		var t = new eui.Group();
		this.ConversionGoods = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 694;
		t.visible = false;
		t.width = 378.79;
		t.x = 71;
		t.y = 212.24;
		t.elementsContent = [this.Change_i(),this.tu1_i(),this.tu2_i(),this.tu3_i(),this.secret1_i(),this.secret2_i(),this.secret3_i(),this.conversion_presenter_1_i(),this.conversion_presenter_2_i(),this.conversion_presenter_3_i(),this._Image76_i(),this.Conversion_money_i(),this._Image77_i(),this._Group1_i(),this.MyOrder_i(),this.Discuss_i(),this.ChangeTheOrder_i(),this.deficiency_i(),this.succeed_i(),this.discuss_i(),this.Select_category_i(),this.address_i(),this.hint3_i()];
		return t;
	};
	_proto.Change_i = function () {
		var t = new eui.Button();
		this.Change = t;
		t.height = 33;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 100;
		t.x = 208.61;
		t.y = -5.15;
		t.skinName = TheFarmLobbySkin$Skin43;
		return t;
	};
	_proto.tu1_i = function () {
		var t = new eui.Image();
		this.tu1 = t;
		t.height = 120;
		t.source = "mianmo_png";
		t.width = 100;
		t.x = 9.07;
		t.y = 90;
		return t;
	};
	_proto.tu2_i = function () {
		var t = new eui.Image();
		this.tu2 = t;
		t.height = 120;
		t.source = "mianmo_png";
		t.width = 100;
		t.x = 132.12;
		t.y = 90;
		return t;
	};
	_proto.tu3_i = function () {
		var t = new eui.Image();
		this.tu3 = t;
		t.height = 120;
		t.source = "mianmo_png";
		t.width = 100;
		t.x = 262.7;
		t.y = 91.78;
		return t;
	};
	_proto.secret1_i = function () {
		var t = new eui.Image();
		this.secret1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 120;
		t.source = "TouMingBeiJing_png";
		t.width = 100;
		t.x = 11.07;
		t.y = 90;
		return t;
	};
	_proto.secret2_i = function () {
		var t = new eui.Image();
		this.secret2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 120;
		t.source = "TouMingBeiJing_png";
		t.width = 100;
		t.x = 133.79;
		t.y = 90;
		return t;
	};
	_proto.secret3_i = function () {
		var t = new eui.Image();
		this.secret3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 120;
		t.source = "TouMingBeiJing_png";
		t.width = 100;
		t.x = 263.31;
		t.y = 90;
		return t;
	};
	_proto.conversion_presenter_1_i = function () {
		var t = new eui.Button();
		this.conversion_presenter_1 = t;
		t.enabled = false;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 9.82;
		t.y = 214;
		t.skinName = TheFarmLobbySkin$Skin44;
		return t;
	};
	_proto.conversion_presenter_2_i = function () {
		var t = new eui.Button();
		this.conversion_presenter_2 = t;
		t.enabled = false;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 137.41;
		t.y = 214;
		t.skinName = TheFarmLobbySkin$Skin45;
		return t;
	};
	_proto.conversion_presenter_3_i = function () {
		var t = new eui.Button();
		this.conversion_presenter_3 = t;
		t.enabled = false;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 261.93;
		t.y = 214;
		t.skinName = TheFarmLobbySkin$Skin46;
		return t;
	};
	_proto._Image76_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 33;
		t.source = "jux@2x(2)_png";
		t.width = 100;
		t.x = 76.86;
		t.y = -6.68;
		return t;
	};
	_proto.Conversion_money_i = function () {
		var t = new eui.Label();
		this.Conversion_money = t;
		t.height = 33;
		t.text = "钱数";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 100;
		t.x = 76.86;
		t.y = -6.68;
		return t;
	};
	_proto._Image77_i = function () {
		var t = new eui.Image();
		t.height = 30;
		t.source = "hulu_png";
		t.width = 20;
		t.x = 51.44;
		t.y = -5.15;
		return t;
	};
	_proto._Group1_i = function () {
		var t = new eui.Group();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75.64;
		t.width = 367.57;
		t.x = 2.23;
		t.y = 296.19;
		t.elementsContent = [this._Image78_i(),this.Modellpause_i(),this.Bill_i()];
		return t;
	};
	_proto._Image78_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = -0.35999999999999943;
		t.left = 0;
		t.right = 4.569999999999993;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "juxing@3x_png";
		t.top = 12;
		return t;
	};
	_proto.Modellpause_i = function () {
		var t = new eui.Image();
		this.Modellpause = t;
		t.height = 64;
		t.source = "shaitu@3x_png";
		t.width = 134;
		t.x = 124.36;
		t.y = 15.16;
		return t;
	};
	_proto.Bill_i = function () {
		var t = new eui.Image();
		this.Bill = t;
		t.height = 70;
		t.source = "wodedingdan@3x_png";
		t.width = 134;
		t.x = 2.35;
		t.y = 14.54;
		return t;
	};
	_proto.MyOrder_i = function () {
		var t = new eui.Group();
		this.MyOrder = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 315.15;
		t.width = 368.19;
		t.x = 2.01;
		t.y = 383.13;
		t.elementsContent = [this.All_i(),this.Obligation_i(),this.ForShipping_i(),this.HasBeenShipped_i(),this.Indent0_i()];
		return t;
	};
	_proto.All_i = function () {
		var t = new eui.Image();
		this.All = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 56;
		t.source = "quanbu@3x_png";
		t.width = 96;
		t.x = -1.52;
		t.y = 0;
		return t;
	};
	_proto.Obligation_i = function () {
		var t = new eui.Image();
		this.Obligation = t;
		t.height = 42;
		t.source = "fukuan@3x_png";
		t.width = 96;
		t.x = 88.16;
		t.y = 3.04;
		return t;
	};
	_proto.ForShipping_i = function () {
		var t = new eui.Image();
		this.ForShipping = t;
		t.height = 42;
		t.source = "peisong@2x(1)_png";
		t.width = 96;
		t.x = 181.88;
		t.y = 4.56;
		return t;
	};
	_proto.HasBeenShipped_i = function () {
		var t = new eui.Image();
		this.HasBeenShipped = t;
		t.height = 42;
		t.source = "peisong@3x_png";
		t.width = 96;
		t.x = 274.38;
		t.y = 4.56;
		return t;
	};
	_proto.Indent0_i = function () {
		var t = new eui.Scroller();
		this.Indent0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 253;
		t.width = 360;
		t.x = 8;
		t.y = 61.82;
		t.viewport = this.indent_particulars_i();
		return t;
	};
	_proto.indent_particulars_i = function () {
		var t = new eui.Group();
		this.indent_particulars = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 291.18;
		t.width = 496.67;
		return t;
	};
	_proto.Discuss_i = function () {
		var t = new eui.Scroller();
		this.Discuss = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 309.21;
		t.visible = false;
		t.width = 481.82;
		t.x = 49.56;
		t.y = 445.6;
		t.viewport = this.DiscussX_i();
		return t;
	};
	_proto.DiscussX_i = function () {
		var t = new eui.Group();
		this.DiscussX = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 221.21;
		t.width = 477.27;
		t.y = -3.03;
		t.elementsContent = [this._Image79_i(),this.MyName_i(),this.Comment_i(),this.CommentTime_i(),this._Image80_i()];
		return t;
	};
	_proto._Image79_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 85.15;
		t.source = "white2_png";
		t.width = 91.21;
		t.x = 33;
		t.y = 29;
		return t;
	};
	_proto.MyName_i = function () {
		var t = new eui.Label();
		this.MyName = t;
		t.text = "名字";
		t.x = 46;
		t.y = 144;
		return t;
	};
	_proto.Comment_i = function () {
		var t = new eui.Label();
		this.Comment = t;
		t.text = "评论";
		t.x = 188;
		t.y = 29;
		return t;
	};
	_proto.CommentTime_i = function () {
		var t = new eui.Label();
		this.CommentTime = t;
		t.text = "时间";
		t.x = 180;
		t.y = 78;
		return t;
	};
	_proto._Image80_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 77.57;
		t.source = "white2_png";
		t.width = 244.24;
		t.x = 182;
		t.y = 135;
		return t;
	};
	_proto.ChangeTheOrder_i = function () {
		var t = new eui.Group();
		this.ChangeTheOrder = t;
		t.height = 480;
		t.scaleX = 1;
		t.scaleY = 1;
		t.visible = false;
		t.width = 520;
		t.x = 38;
		t.y = 23;
		t.elementsContent = [this._Image81_i(),this._Image82_i(),this.ChangeTheOrder_close_i(),this._Image83_i(),this.exchange_i()];
		return t;
	};
	_proto._Image81_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -96;
		t.y = -257;
		return t;
	};
	_proto._Image82_i = function () {
		var t = new eui.Image();
		t.height = 480;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "turquoise_png";
		t.width = 520;
		t.x = 2;
		t.y = -1;
		return t;
	};
	_proto.ChangeTheOrder_close_i = function () {
		var t = new eui.Button();
		this.ChangeTheOrder_close = t;
		t.label = "关闭";
		t.x = 421.94;
		t.y = 5;
		return t;
	};
	_proto._Image83_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 90;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "z9_png";
		t.width = 333;
		t.x = 92;
		t.y = -1;
		return t;
	};
	_proto.exchange_i = function () {
		var t = new eui.Scroller();
		this.exchange = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 350;
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 480;
		t.x = 22;
		t.y = 99;
		t.viewport = this._Group2_i();
		return t;
	};
	_proto._Group2_i = function () {
		var t = new eui.Group();
		t.anchorOffsetY = 0;
		t.height = 106.06;
		t.elementsContent = [this.ExchangeForm_i()];
		return t;
	};
	_proto.ExchangeForm_i = function () {
		var t = new eui.Group();
		this.ExchangeForm = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 120;
		t.width = 420;
		t.x = 30;
		t.y = 20;
		t.elementsContent = [this.xylophyta0_i(),this.TimeCapsule0_i(),this.DeliverGoods0_i()];
		return t;
	};
	_proto.xylophyta0_i = function () {
		var t = new eui.Label();
		this.xylophyta0 = t;
		t.text = "植物";
		t.x = 23;
		t.y = 18;
		return t;
	};
	_proto.TimeCapsule0_i = function () {
		var t = new eui.Label();
		this.TimeCapsule0 = t;
		t.text = "时间";
		t.x = 23;
		t.y = 60;
		return t;
	};
	_proto.DeliverGoods0_i = function () {
		var t = new eui.Image();
		this.DeliverGoods0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 68.48;
		t.source = "white2_png";
		t.width = 95.76;
		t.x = 267;
		t.y = 28;
		return t;
	};
	_proto.deficiency_i = function () {
		var t = new eui.Group();
		this.deficiency = t;
		t.anchorOffsetX = 0;
		t.height = 400;
		t.visible = false;
		t.width = 500;
		t.x = 65;
		t.y = 158.28;
		t.elementsContent = [this._Image84_i(),this._Image85_i(),this.deficiency_text_i(),this._Image86_i(),this.shut1_i()];
		return t;
	};
	_proto._Image84_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -120.32;
		t.y = -391.34;
		return t;
	};
	_proto._Image85_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.height = 4;
		t.left = 0;
		t.right = 0;
		t.source = "beijing3_png";
		t.top = 0;
		return t;
	};
	_proto.deficiency_text_i = function () {
		var t = new eui.Label();
		this.deficiency_text = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 218;
		t.text = "抱歉您的葫芦币不足";
		t.textColor = 0x000000;
		t.width = 391.33;
		t.x = 53.42;
		t.y = 63.04;
		return t;
	};
	_proto._Image86_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 81.33;
		t.source = "tishi_png";
		t.width = 326.67;
		t.x = 78.97;
		t.y = -34.94;
		return t;
	};
	_proto.shut1_i = function () {
		var t = new eui.Button();
		this.shut1 = t;
		t.label = "退出";
		t.x = 185.16;
		t.y = 306.27;
		return t;
	};
	_proto.succeed_i = function () {
		var t = new eui.Group();
		this.succeed = t;
		t.height = 400;
		t.visible = false;
		t.width = 500;
		t.x = 50;
		t.y = 200;
		t.elementsContent = [this._Image87_i(),this._Image88_i(),this._Label22_i(),this._Image89_i(),this.shut2_i()];
		return t;
	};
	_proto._Image87_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -102.7;
		t.y = -432;
		return t;
	};
	_proto._Image88_i = function () {
		var t = new eui.Image();
		t.bottom = 47;
		t.left = 3;
		t.right = -3;
		t.source = "beijing3_png";
		t.top = -47;
		return t;
	};
	_proto._Label22_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 102;
		t.multiline = true;
		t.text = "       恭喜您兑换成功，我们马上开始发货，请耐心等待";
		t.textColor = 0x000000;
		t.width = 422.67;
		t.x = 43.09;
		t.y = 23;
		return t;
	};
	_proto._Image89_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 83.64;
		t.source = "tishi_png";
		t.width = 304.85;
		t.x = 105.05;
		t.y = -81.08;
		return t;
	};
	_proto.shut2_i = function () {
		var t = new eui.Button();
		this.shut2 = t;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 200;
		t.y = 246.69;
		t.skinName = TheFarmLobbySkin$Skin47;
		return t;
	};
	_proto.discuss_i = function () {
		var t = new eui.Group();
		this.discuss = t;
		t.height = 456;
		t.visible = false;
		t.width = 500;
		t.x = 50;
		t.y = 85;
		t.elementsContent = [this._Image90_i(),this._Image91_i(),this._Image92_i(),this.discuss_text_i(),this.discuss_tu_i(),this.discuss_yes_i(),this._Label23_i(),this.discuss_close_i()];
		return t;
	};
	_proto._Image90_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -106.96;
		t.y = -318.03;
		return t;
	};
	_proto._Image91_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.height = 480;
		t.left = 0;
		t.right = 0;
		t.source = "beijing3_png";
		t.top = 0;
		return t;
	};
	_proto._Image92_i = function () {
		var t = new eui.Image();
		t.height = 100;
		t.source = "shaitu(1)_png";
		t.width = 300;
		t.x = 100;
		t.y = -49.36;
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
		t.width = 435;
		t.x = 30;
		t.y = 72;
		return t;
	};
	_proto.discuss_tu_i = function () {
		var t = new eui.Image();
		this.discuss_tu = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 130;
		t.source = "tianjia(1)_png";
		t.touchEnabled = true;
		t.width = 140;
		t.x = 26;
		t.y = 244.24;
		return t;
	};
	_proto.discuss_yes_i = function () {
		var t = new eui.Button();
		this.discuss_yes = t;
		t.height = 60;
		t.label = "";
		t.width = 120;
		t.x = 189;
		t.y = 372.9;
		t.skinName = TheFarmLobbySkin$Skin48;
		return t;
	};
	_proto._Label23_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 83.03;
		t.text = "发表评论即可获得肥料";
		t.textColor = 0x000000;
		t.width = 269.7;
		t.x = 186.82;
		t.y = 261.25;
		return t;
	};
	_proto.discuss_close_i = function () {
		var t = new eui.Button();
		this.discuss_close = t;
		t.height = 70;
		t.label = "";
		t.width = 70;
		t.x = 431.82;
		t.y = -15.73;
		t.skinName = TheFarmLobbySkin$Skin49;
		return t;
	};
	_proto.Select_category_i = function () {
		var t = new eui.Group();
		this.Select_category = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 672;
		t.visible = false;
		t.width = 516;
		t.x = 36;
		t.y = -10;
		t.elementsContent = [this._Image93_i(),this._Image94_i(),this.Select_category_close_i(),this._Image95_i(),this.Xtu_i(),this.Xname_i(),this._Label24_i(),this.Xshu_i(),this._Button8_i()];
		return t;
	};
	_proto._Image93_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = -189;
		t.left = -90;
		t.right = -112;
		t.source = "TouMingBeiJing_png";
		t.top = -221;
		return t;
	};
	_proto._Image94_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 686;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "beijing3_png";
		t.width = 440.18;
		t.x = -63.5;
		t.y = -36.68;
		return t;
	};
	_proto.Select_category_close_i = function () {
		var t = new eui.Button();
		this.Select_category_close = t;
		t.height = 80;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 80;
		t.x = 446;
		t.y = -56;
		t.skinName = TheFarmLobbySkin$Skin50;
		return t;
	};
	_proto._Image95_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 106;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "pinlei_png";
		t.width = 390;
		t.x = 64;
		t.y = -83;
		return t;
	};
	_proto.Xtu_i = function () {
		var t = new eui.Image();
		this.Xtu = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "white2_png";
		t.width = 100;
		t.x = 30;
		t.y = 132;
		return t;
	};
	_proto.Xname_i = function () {
		var t = new eui.Label();
		this.Xname = t;
		t.size = 40;
		t.text = "Label";
		t.textColor = 0x000000;
		t.x = 150;
		t.y = 132.88;
		return t;
	};
	_proto._Label24_i = function () {
		var t = new eui.Label();
		t.text = "可兑换数量：";
		t.textColor = 0x000000;
		t.x = 150;
		t.y = 193;
		return t;
	};
	_proto.Xshu_i = function () {
		var t = new eui.Label();
		this.Xshu = t;
		t.anchorOffsetX = 0;
		t.text = "Label";
		t.textColor = 0x000000;
		t.width = 132;
		t.x = 327.15;
		t.y = 195;
		return t;
	};
	_proto._Button8_i = function () {
		var t = new eui.Button();
		t.label = "";
		t.x = 434;
		t.y = 132.88;
		t.skinName = TheFarmLobbySkin$Skin51;
		return t;
	};
	_proto.address_i = function () {
		var t = new eui.Group();
		this.address = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 681;
		t.visible = false;
		t.width = 512;
		t.x = 32;
		t.y = -46;
		t.elementsContent = [this._Image96_i(),this._Image97_i(),this.receiver_address_i(),this.receiver_name_i(),this._Image98_i(),this.address_close_i(),this._Label25_i(),this._Label26_i(),this._Label27_i(),this.receiver_number_i(),this.address_yes_i()];
		return t;
	};
	_proto._Image96_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -86.96;
		t.y = -184.06;
		return t;
	};
	_proto._Image97_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "beijing3_png";
		t.top = 0;
		return t;
	};
	_proto.receiver_address_i = function () {
		var t = new eui.TextInput();
		this.receiver_address = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 240;
		t.prompt = "请填写详细收货地址";
		t.width = 256;
		t.x = 210;
		t.y = 267;
		return t;
	};
	_proto.receiver_name_i = function () {
		var t = new eui.TextInput();
		this.receiver_name = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 36;
		t.prompt = "请填写收货人姓名";
		t.width = 256;
		t.x = 210;
		t.y = 123;
		return t;
	};
	_proto._Image98_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 112;
		t.source = "tishi_png";
		t.width = 356;
		t.x = 72;
		t.y = -38;
		return t;
	};
	_proto.address_close_i = function () {
		var t = new eui.Button();
		this.address_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 424;
		t.y = -24;
		t.skinName = TheFarmLobbySkin$Skin52;
		return t;
	};
	_proto._Label25_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 36;
		t.text = "联系人";
		t.textColor = 0x000000;
		t.x = 40;
		t.y = 123;
		return t;
	};
	_proto._Label26_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 36;
		t.text = "联系电话";
		t.textColor = 0x000000;
		t.x = 40;
		t.y = 200;
		return t;
	};
	_proto._Label27_i = function () {
		var t = new eui.Label();
		t.bold = true;
		t.size = 36;
		t.text = "联系地址";
		t.textColor = 0x000000;
		t.x = 40;
		t.y = 267;
		return t;
	};
	_proto.receiver_number_i = function () {
		var t = new eui.TextInput();
		this.receiver_number = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 36;
		t.maxChars = 11;
		t.prompt = "请填写收货人联系方式";
		t.restrict = "\"0-9\"";
		t.width = 256;
		t.x = 210;
		t.y = 200;
		return t;
	};
	_proto.address_yes_i = function () {
		var t = new eui.Button();
		this.address_yes = t;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 195.88;
		t.y = 561;
		t.skinName = TheFarmLobbySkin$Skin53;
		return t;
	};
	_proto.hint3_i = function () {
		var t = new eui.Group();
		this.hint3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 463;
		t.visible = false;
		t.width = 533;
		t.x = 44;
		t.y = -2;
		t.elementsContent = [this._Image99_i(),this._Image100_i(),this._Image101_i(),this._Image102_i(),this.hint3_colse_i(),this.hint3_yes_i()];
		return t;
	};
	_proto._Image99_i = function () {
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
	_proto._Image100_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "beijing3_png";
		t.top = 0;
		return t;
	};
	_proto._Image101_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 106.36;
		t.source = "tishi_png";
		t.width = 344.24;
		t.x = 88.88;
		t.y = -44.36;
		return t;
	};
	_proto._Image102_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 57.88;
		t.source = "weishangjia_png";
		t.width = 316.97;
		t.x = 104.79;
		t.y = 180.52;
		return t;
	};
	_proto.hint3_colse_i = function () {
		var t = new eui.Button();
		this.hint3_colse = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 438;
		t.y = -1;
		t.skinName = TheFarmLobbySkin$Skin54;
		return t;
	};
	_proto.hint3_yes_i = function () {
		var t = new eui.Button();
		this.hint3_yes = t;
		t.height = 50;
		t.label = "";
		t.width = 100;
		t.x = 215;
		t.y = 357;
		t.skinName = TheFarmLobbySkin$Skin55;
		return t;
	};
	_proto.friend_i = function () {
		var t = new eui.Group();
		this.friend = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image103_i(),this._Image104_i(),this.Best_i(),this.Attention_i(),this.Pick_i(),this.REGISTRATION_i(),this.PrivateLetter_i(),this.AddFriend_i(),this._Image105_i(),this.Parameters_i(),this._Image106_i(),this._Image107_i(),this._Image108_i(),this.friend_money_i(),this.friend_close_i(),this.MeetPeople_i(),this.Chitchat_i(),this.ApplyFor_i(),this.FindMyPhone_i(),this.FriendSet_i()];
		return t;
	};
	_proto._Image103_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image104_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 924;
		t.source = "beijing3_png";
		t.width = 446.06;
		t.x = 26.32;
		t.y = 40;
		return t;
	};
	_proto.Best_i = function () {
		var t = new eui.Image();
		this.Best = t;
		t.height = 42;
		t.source = "paihang@3x_png";
		t.width = 60;
		t.x = 48.01;
		t.y = 212;
		return t;
	};
	_proto.Attention_i = function () {
		var t = new eui.Image();
		this.Attention = t;
		t.height = 42;
		t.source = "guanzhu@3x_png";
		t.width = 60;
		t.x = 108.07;
		t.y = 212;
		return t;
	};
	_proto.Pick_i = function () {
		var t = new eui.Image();
		this.Pick = t;
		t.height = 42;
		t.source = "kezhai@3x_png";
		t.width = 60;
		t.x = 166.71;
		t.y = 212;
		return t;
	};
	_proto.REGISTRATION_i = function () {
		var t = new eui.Image();
		this.REGISTRATION = t;
		t.height = 42;
		t.source = "shenqing@3x_png";
		t.width = 60;
		t.x = 225.39;
		t.y = 212;
		return t;
	};
	_proto.PrivateLetter_i = function () {
		var t = new eui.Image();
		this.PrivateLetter = t;
		t.height = 42;
		t.source = "sixin@3x_png";
		t.width = 60;
		t.x = 282.35;
		t.y = 212;
		return t;
	};
	_proto.AddFriend_i = function () {
		var t = new eui.Button();
		this.AddFriend = t;
		t.height = 42;
		t.label = "";
		t.width = 96;
		t.x = 351.47;
		t.y = 212;
		t.skinName = TheFarmLobbySkin$Skin56;
		return t;
	};
	_proto._Image105_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 75.33;
		t.source = "fenge@3x_png";
		t.width = 436;
		t.x = 34.67;
		t.y = 135;
		return t;
	};
	_proto.Parameters_i = function () {
		var t = new eui.Image();
		this.Parameters = t;
		t.anchorOffsetX = 0;
		t.height = 32;
		t.source = "shezhi_png";
		t.width = 80;
		t.x = 311.03;
		t.y = 142;
		return t;
	};
	_proto._Image106_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 58;
		t.source = "haoyou_png";
		t.width = 256;
		t.x = 113.31;
		t.y = 46;
		return t;
	};
	_proto._Image107_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 43;
		t.source = "hulu_png";
		t.width = 32;
		t.x = 108.67;
		t.y = 138.34;
		return t;
	};
	_proto._Image108_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 42;
		t.source = "juxing@2x(2)_png";
		t.width = 138;
		t.x = 142.35;
		t.y = 142;
		return t;
	};
	_proto.friend_money_i = function () {
		var t = new eui.Label();
		this.friend_money = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 22;
		t.size = 24;
		t.text = "0";
		t.textAlign = "center";
		t.verticalAlign = "middle";
		t.width = 117.33;
		t.x = 146.35;
		t.y = 151;
		return t;
	};
	_proto.friend_close_i = function () {
		var t = new eui.Button();
		this.friend_close = t;
		t.height = 70;
		t.label = "";
		t.width = 70;
		t.x = 376;
		t.y = 40;
		t.skinName = TheFarmLobbySkin$Skin57;
		return t;
	};
	_proto.MeetPeople_i = function () {
		var t = new eui.Scroller();
		this.MeetPeople = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 534.3;
		t.width = 384;
		t.x = 55;
		t.y = 300;
		t.viewport = this.figure_i();
		return t;
	};
	_proto.figure_i = function () {
		var t = new eui.Group();
		this.figure = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 516.12;
		t.width = 580;
		t.x = -2;
		return t;
	};
	_proto.Chitchat_i = function () {
		var t = new eui.Group();
		this.Chitchat = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 576.6;
		t.width = 383.33;
		t.x = 57.23;
		t.y = 259.09;
		t.elementsContent = [this._Image109_i(),this.PeopleChat_i(),this.ChatTools_i(),this.ChatFrame_i(),this.ChatIcon_i(),this.Send_i()];
		return t;
	};
	_proto._Image109_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 576.8;
		t.source = "juxingt_png";
		t.width = 383.64;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto.PeopleChat_i = function () {
		var t = new eui.Button();
		this.PeopleChat = t;
		t.height = 40;
		t.label = "Button";
		t.width = 80;
		t.x = -4.22;
		t.y = 592.77;
		return t;
	};
	_proto.ChatTools_i = function () {
		var t = new eui.Scroller();
		this.ChatTools = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 573.76;
		t.width = 380.3;
		t.x = 1;
		t.y = 1;
		t.viewport = this._Group3_i();
		return t;
	};
	_proto._Group3_i = function () {
		var t = new eui.Group();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 544.97;
		t.width = 540.9;
		return t;
	};
	_proto.ChatFrame_i = function () {
		var t = new eui.TextInput();
		this.ChatFrame = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.width = 180;
		t.x = 81.92;
		t.y = 592.78;
		return t;
	};
	_proto.ChatIcon_i = function () {
		var t = new eui.Button();
		this.ChatIcon = t;
		t.anchorOffsetX = 0;
		t.height = 40;
		t.label = "";
		t.width = 40;
		t.x = 269.39;
		t.y = 594.3;
		t.skinName = TheFarmLobbySkin$Skin58;
		return t;
	};
	_proto.Send_i = function () {
		var t = new eui.Button();
		this.Send = t;
		t.anchorOffsetX = 0;
		t.label = "发送";
		t.width = 70;
		t.x = 317.78;
		t.y = 591.26;
		return t;
	};
	_proto.ApplyFor_i = function () {
		var t = new eui.Scroller();
		this.ApplyFor = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 578.79;
		t.visible = false;
		t.width = 380.3;
		t.x = 58.71;
		t.y = 257.57;
		t.viewport = this._Group5_i();
		return t;
	};
	_proto._Group5_i = function () {
		var t = new eui.Group();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 692.43;
		t.width = 522.72;
		t.x = -1.52;
		t.y = -3.03;
		t.elementsContent = [this._Group4_i()];
		return t;
	};
	_proto._Group4_i = function () {
		var t = new eui.Group();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 140.91;
		t.width = 345.46;
		t.x = 17;
		t.y = 19;
		t.elementsContent = [this._Image110_i(),this.Moniker_i(),this.SendTo_i(),this.Status_i(),this._Button9_i(),this._Image111_i()];
		return t;
	};
	_proto._Image110_i = function () {
		var t = new eui.Image();
		t.bottom = 0;
		t.left = 0;
		t.right = 0;
		t.source = "juxing(10)_png";
		t.top = 0;
		return t;
	};
	_proto.Moniker_i = function () {
		var t = new eui.Label();
		this.Moniker = t;
		t.text = "名字";
		t.textColor = 0x000000;
		t.x = 23;
		t.y = 42;
		return t;
	};
	_proto.SendTo_i = function () {
		var t = new eui.Label();
		this.SendTo = t;
		t.text = "手机号码";
		t.textColor = 0x000000;
		t.x = 132;
		t.y = 42;
		return t;
	};
	_proto.Status_i = function () {
		var t = new eui.Button();
		this.Status = t;
		t.height = 70;
		t.label = "";
		t.width = 70;
		t.x = 348;
		t.y = 40;
		t.skinName = TheFarmLobbySkin$Skin59;
		return t;
	};
	_proto._Button9_i = function () {
		var t = new eui.Button();
		t.height = 70;
		t.label = "";
		t.width = 70;
		t.x = 420;
		t.y = 40;
		t.skinName = TheFarmLobbySkin$Skin60;
		return t;
	};
	_proto._Image111_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "yitianjia_png";
		t.visible = false;
		t.width = 100;
		t.x = 360;
		t.y = 20;
		return t;
	};
	_proto.FindMyPhone_i = function () {
		var t = new eui.Group();
		this.FindMyPhone = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 825.76;
		t.visible = false;
		t.width = 563.64;
		t.x = 80;
		t.y = 132;
		t.elementsContent = [this._Image112_i(),this.FindMyPhone_close_i(),this.PhoneNumber_i(),this.Find_i(),this.PlayerList_i(),this._Image113_i(),this._Image114_i()];
		return t;
	};
	_proto._Image112_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 1000;
		t.source = "bj_png";
		t.width = 640;
		t.x = -40;
		t.y = -96;
		return t;
	};
	_proto.FindMyPhone_close_i = function () {
		var t = new eui.Button();
		this.FindMyPhone_close = t;
		t.height = 90;
		t.label = "";
		t.width = 90;
		t.x = 485;
		t.y = -97;
		t.skinName = TheFarmLobbySkin$Skin61;
		return t;
	};
	_proto.PhoneNumber_i = function () {
		var t = new eui.TextInput();
		this.PhoneNumber = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 270;
		t.x = 226;
		t.y = 24;
		return t;
	};
	_proto.Find_i = function () {
		var t = new eui.Button();
		this.Find = t;
		t.height = 60;
		t.label = "";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 120;
		t.x = 186.67;
		t.y = 81;
		t.skinName = TheFarmLobbySkin$Skin62;
		return t;
	};
	_proto.PlayerList_i = function () {
		var t = new eui.Scroller();
		this.PlayerList = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 615.15;
		t.width = 506.06;
		t.x = 25;
		t.y = 198;
		t.viewport = this._Group6_i();
		return t;
	};
	_proto._Group6_i = function () {
		var t = new eui.Group();
		t.elementsContent = [this.PlayerInformation_i()];
		return t;
	};
	_proto.PlayerInformation_i = function () {
		var t = new eui.Group();
		this.PlayerInformation = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 140.91;
		t.width = 500;
		t.x = 4;
		t.elementsContent = [this.Rank_i(),this.ChatHead_i(),this.FaceIcon_i(),this.BestRating_i(),this.Addition_i(),this.LevenshteinDistance_i()];
		return t;
	};
	_proto.Rank_i = function () {
		var t = new eui.Image();
		this.Rank = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.width = 100;
		t.x = 9.79;
		t.y = 10.18;
		return t;
	};
	_proto.ChatHead_i = function () {
		var t = new eui.Image();
		this.ChatHead = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.width = 100;
		t.x = 146.79;
		t.y = 10.18;
		return t;
	};
	_proto.FaceIcon_i = function () {
		var t = new eui.Image();
		this.FaceIcon = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 32.12;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "white2_png";
		t.width = 39.7;
		t.x = 271.79;
		t.y = 19.18;
		return t;
	};
	_proto.BestRating_i = function () {
		var t = new eui.Label();
		this.BestRating = t;
		t.scaleX = 1;
		t.scaleY = 1;
		t.text = "钻石地主";
		t.x = 311.21;
		t.y = 21.3;
		return t;
	};
	_proto.Addition_i = function () {
		var t = new eui.Button();
		this.Addition = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 37.58;
		t.label = "添加";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 49.7;
		t.x = 439.79;
		t.y = 29;
		return t;
	};
	_proto.LevenshteinDistance_i = function () {
		var t = new eui.Label();
		this.LevenshteinDistance = t;
		t.scaleX = 1;
		t.scaleY = 1;
		t.text = "500m";
		t.x = 437.79;
		t.y = 83.18;
		return t;
	};
	_proto._Image113_i = function () {
		var t = new eui.Image();
		t.height = 40;
		t.source = "sousuo_png";
		t.width = 210;
		t.x = 6;
		t.y = 24;
		return t;
	};
	_proto._Image114_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 50;
		t.source = "fujinwanjia_png";
		t.width = 150;
		t.x = 30;
		t.y = 140;
		return t;
	};
	_proto.FriendSet_i = function () {
		var t = new eui.Group();
		this.FriendSet = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 500;
		t.visible = false;
		t.width = 531.81;
		t.x = 113.5;
		t.y = 265;
		t.elementsContent = [this._Image115_i(),this._Image116_i(),this.FriendSet_close_i(),this.MobileSearch_i(),this.RecommendationFriend_i(),this._Image117_i(),this._Image118_i(),this._Image119_i(),this._Image120_i()];
		return t;
	};
	_proto._Image115_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = -124;
		t.y = -218;
		return t;
	};
	_proto._Image116_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bottom = 24;
		t.height = 600;
		t.left = 2;
		t.right = 40;
		t.source = "set2_png";
		t.top = 46;
		t.width = 600;
		return t;
	};
	_proto.FriendSet_close_i = function () {
		var t = new eui.Button();
		this.FriendSet_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 356.52;
		t.y = 64.11;
		t.skinName = TheFarmLobbySkin$Skin63;
		return t;
	};
	_proto.MobileSearch_i = function () {
		var t = new eui.ToggleSwitch();
		this.MobileSearch = t;
		t.height = 32;
		t.label = "ToggleButton";
		t.width = 80;
		t.x = 351.52;
		t.y = 220.52;
		return t;
	};
	_proto.RecommendationFriend_i = function () {
		var t = new eui.ToggleSwitch();
		this.RecommendationFriend = t;
		t.height = 32;
		t.label = "ToggleButton";
		t.width = 80;
		t.x = 351.52;
		t.y = 339;
		return t;
	};
	_proto._Image117_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 60;
		t.source = "shoujihao_png";
		t.width = 270;
		t.x = 41.52;
		t.y = 204.52;
		return t;
	};
	_proto._Image118_i = function () {
		var t = new eui.Image();
		t.height = 60;
		t.source = "fujinren_png";
		t.width = 270;
		t.x = 41.52;
		t.y = 330.52;
		return t;
	};
	_proto._Image119_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 76.06;
		t.source = "jhda_png";
		t.width = 245.76;
		t.x = 91.52;
		t.y = 61.08;
		return t;
	};
	_proto._Image120_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 48.79;
		t.source = "set_png";
		t.width = 156.36;
		t.x = 139.52;
		t.y = 71.52;
		return t;
	};
	_proto.TASK_i = function () {
		var t = new eui.Group();
		this.TASK = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.y = 0;
		t.elementsContent = [this._Image121_i(),this._Image122_i(),this._Image123_i(),this._Image124_i(),this._Image125_i(),this._Image126_i(),this._Image127_i(),this.TASK_close_i(),this._Image128_i(),this._Image129_i(),this._Image130_i(),this._Image131_i(),this._Image132_i(),this._Label28_i(),this._Label29_i(),this._Label30_i(),this._Label31_i(),this._Label32_i(),this._Image133_i(),this._Image134_i(),this._Image135_i(),this._Image136_i(),this._Image137_i(),this._Image138_i(),this._Image139_i(),this._Image140_i(),this._Image141_i(),this._Image142_i(),this._Image143_i(),this._Button10_i(),this._Button11_i(),this._Button12_i(),this._Button13_i(),this._Button14_i(),this.Task2_i(),this.HebdomadTask1_i()];
		return t;
	};
	_proto._Image121_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image122_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 900;
		t.source = "bj2_png";
		t.width = 480;
		t.x = 10;
		t.y = 40;
		return t;
	};
	_proto._Image123_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "rectangle12_png";
		t.width = 426;
		t.x = 37;
		t.y = 241.6;
		return t;
	};
	_proto._Image124_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "rectangle12_png";
		t.width = 426;
		t.x = 37;
		t.y = 352.18;
		return t;
	};
	_proto._Image125_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "rectangle12_png";
		t.width = 426;
		t.x = 37;
		t.y = 461.3;
		return t;
	};
	_proto._Image126_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "rectangle12_png";
		t.width = 426;
		t.x = 37;
		t.y = 565.86;
		return t;
	};
	_proto._Image127_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 100;
		t.source = "rectangle12_png";
		t.width = 426;
		t.x = 37;
		t.y = 676.4;
		return t;
	};
	_proto.TASK_close_i = function () {
		var t = new eui.Button();
		this.TASK_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 381.24;
		t.y = 50;
		t.skinName = TheFarmLobbySkin$Skin64;
		return t;
	};
	_proto._Image128_i = function () {
		var t = new eui.Image();
		t.height = 80;
		t.source = "fenxiang_png";
		t.width = 80;
		t.x = 42;
		t.y = 251.52;
		return t;
	};
	_proto._Image129_i = function () {
		var t = new eui.Image();
		t.height = 80;
		t.source = "qiandao_png";
		t.width = 80;
		t.x = 42;
		t.y = 363.62;
		return t;
	};
	_proto._Image130_i = function () {
		var t = new eui.Image();
		t.height = 80;
		t.source = "haoyou2_png";
		t.width = 80;
		t.x = 42;
		t.y = 477.3;
		return t;
	};
	_proto._Image131_i = function () {
		var t = new eui.Image();
		t.height = 80;
		t.source = "wabao_png";
		t.width = 80;
		t.x = 42;
		t.y = 579.86;
		return t;
	};
	_proto._Image132_i = function () {
		var t = new eui.Image();
		t.height = 80;
		t.source = "shaitu_png";
		t.width = 80;
		t.x = 42;
		t.y = 690.4;
		return t;
	};
	_proto._Label28_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 60;
		t.size = 18;
		t.text = "分享任务：分享农品街1次可获得以下奖励";
		t.textColor = 0x000000;
		t.width = 340;
		t.x = 130;
		t.y = 250;
		return t;
	};
	_proto._Label29_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 60;
		t.size = 18;
		t.text = "签到任务：每日签到可领取以下奖励";
		t.textColor = 0x000000;
		t.width = 340;
		t.x = 130;
		t.y = 362.18;
		return t;
	};
	_proto._Label30_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.height = 60;
		t.size = 18;
		t.text = "好友任务：好友达到10人可领取以下奖励";
		t.textColor = 0x000000;
		t.width = 340;
		t.x = 130;
		t.y = 471.3;
		return t;
	};
	_proto._Label31_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.size = 18;
		t.text = "挖宝任务：每日挖宝一次可领取以下奖励";
		t.textColor = 0x000000;
		t.width = 340;
		t.x = 130;
		t.y = 574.34;
		return t;
	};
	_proto._Label32_i = function () {
		var t = new eui.Label();
		t.anchorOffsetX = 0;
		t.size = 18;
		t.text = "晒图任务：葫芦币兑换成功并晒图分享";
		t.textColor = 0x000000;
		t.width = 340;
		t.x = 130;
		t.y = 686.4;
		return t;
	};
	_proto._Image133_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 56.79;
		t.source = "renwu_png";
		t.width = 254.85;
		t.x = 105.3;
		t.y = 62;
		return t;
	};
	_proto._Image134_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shifei_png";
		t.width = 50;
		t.x = 160;
		t.y = 283.6;
		return t;
	};
	_proto._Image135_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shuihu_png";
		t.width = 50;
		t.x = 240;
		t.y = 285.12;
		return t;
	};
	_proto._Image136_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shifei_png";
		t.width = 50;
		t.x = 160;
		t.y = 395.7;
		return t;
	};
	_proto._Image137_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shuihu_png";
		t.width = 50;
		t.x = 240;
		t.y = 397.22;
		return t;
	};
	_proto._Image138_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shifei_png";
		t.width = 50;
		t.x = 160;
		t.y = 503.3;
		return t;
	};
	_proto._Image139_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shuihu_png";
		t.width = 50;
		t.x = 240;
		t.y = 503.3;
		return t;
	};
	_proto._Image140_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shifei_png";
		t.width = 50;
		t.x = 160;
		t.y = 606.34;
		return t;
	};
	_proto._Image141_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shuihu_png";
		t.width = 50;
		t.x = 240;
		t.y = 606.34;
		return t;
	};
	_proto._Image142_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shifei_png";
		t.width = 50;
		t.x = 160;
		t.y = 718.4;
		return t;
	};
	_proto._Image143_i = function () {
		var t = new eui.Image();
		t.height = 50;
		t.source = "shuihu_png";
		t.width = 50;
		t.x = 240;
		t.y = 718.4;
		return t;
	};
	_proto._Button10_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = false;
		t.height = 40;
		t.label = "";
		t.width = 80;
		t.x = 360;
		t.y = 286.09;
		t.skinName = TheFarmLobbySkin$Skin65;
		return t;
	};
	_proto._Button11_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = true;
		t.height = 40;
		t.label = "";
		t.width = 120;
		t.x = 333.33;
		t.y = 401.22;
		t.skinName = TheFarmLobbySkin$Skin66;
		return t;
	};
	_proto._Button12_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = false;
		t.height = 40;
		t.label = "";
		t.width = 80;
		t.x = 360;
		t.y = 503;
		t.skinName = TheFarmLobbySkin$Skin67;
		return t;
	};
	_proto._Button13_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = false;
		t.height = 40;
		t.label = "";
		t.touchEnabled = false;
		t.width = 80;
		t.x = 360;
		t.y = 606;
		t.skinName = TheFarmLobbySkin$Skin68;
		return t;
	};
	_proto._Button14_i = function () {
		var t = new eui.Button();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = false;
		t.height = 40;
		t.label = "";
		t.width = 80;
		t.x = 360;
		t.y = 718;
		t.skinName = TheFarmLobbySkin$Skin69;
		return t;
	};
	_proto.Task2_i = function () {
		var t = new eui.Image();
		this.Task2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 64;
		t.source = "xianshi@3x_png";
		t.width = 180;
		t.x = 81.61;
		t.y = 150;
		return t;
	};
	_proto.HebdomadTask1_i = function () {
		var t = new eui.Image();
		this.HebdomadTask1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 64;
		t.source = "changgui_png";
		t.width = 180;
		t.x = 272.93;
		t.y = 150;
		return t;
	};
	_proto.DeadlineTASK_i = function () {
		var t = new eui.Group();
		this.DeadlineTASK = t;
		t.height = 1000;
		t.visible = false;
		t.width = 500;
		t.x = 0;
		t.y = 0;
		t.elementsContent = [this._Image144_i(),this._Image145_i(),this._Image146_i(),this.DeadlineTASK_close_i(),this._Image147_i(),this._Image148_i(),this._Image149_i(),this._Image150_i(),this.day_i(),this.day0_i(),this.day1_i(),this.day2_i(),this.day3_i(),this.day4_i(),this.day5_i(),this.OneDays_i(),this.TowDays_i(),this.ThreeDays_i(),this.FourDays_i(),this.FiveDays_i(),this.SixDays_i(),this.SevenDays_i()];
		return t;
	};
	_proto._Image144_i = function () {
		var t = new eui.Image();
		t.height = 1000;
		t.source = "TouMingBeiJing_png";
		t.width = 500;
		t.x = 0;
		t.y = 0;
		return t;
	};
	_proto._Image145_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 788.79;
		t.source = "bj2_png";
		t.width = 480;
		t.x = 10;
		t.y = 80;
		return t;
	};
	_proto._Image146_i = function () {
		var t = new eui.Image();
		t.height = 84;
		t.source = "Seven_days_carnival_png";
		t.width = 420;
		t.x = 40;
		t.y = 265.15;
		return t;
	};
	_proto.DeadlineTASK_close_i = function () {
		var t = new eui.Button();
		this.DeadlineTASK_close = t;
		t.height = 80;
		t.label = "";
		t.width = 80;
		t.x = 393.94;
		t.y = 87.13;
		t.skinName = TheFarmLobbySkin$Skin70;
		return t;
	};
	_proto._Image147_i = function () {
		var t = new eui.Image();
		t.height = 64;
		t.source = "XSRW@3x_png";
		t.width = 180;
		t.x = 76.97;
		t.y = 198;
		return t;
	};
	_proto._Image148_i = function () {
		var t = new eui.Image();
		t.height = 64;
		t.source = "CGRW@3x_png";
		t.width = 180;
		t.x = 252.22;
		t.y = 197.6;
		return t;
	};
	_proto._Image149_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 226.67;
		t.source = "juxing3_png";
		t.width = 426.67;
		t.x = 36.92;
		t.y = 349.68;
		return t;
	};
	_proto._Image150_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 124.85;
		t.source = "jx_png";
		t.width = 416.06;
		t.x = 39.09;
		t.y = 662.29;
		return t;
	};
	_proto.day_i = function () {
		var t = new eui.Group();
		this.day = t;
		t.anchorOffsetY = 0;
		t.height = 333;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this._Image151_i(),this.TaskOne_i(),this.TaskThree_i(),this.OneDays_task2_i(),this._Label33_i(),this.DrillThree_i(),this.AwardFour_i(),this.AwardThree_i(),this.AwardTow_i(),this.get_i(),this.OneDays_task1_i(),this._Label34_i(),this._Image152_i()];
		return t;
	};
	_proto._Image151_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -50;
		return t;
	};
	_proto.TaskOne_i = function () {
		var t = new eui.Label();
		this.TaskOne = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.fontFamily = "Arial";
		t.height = 40;
		t.size = 24;
		t.text = "种植一颗种子";
		t.textColor = 0x000000;
		t.width = 275.76;
		t.x = 80;
		t.y = -40;
		return t;
	};
	_proto.TaskThree_i = function () {
		var t = new eui.Label();
		this.TaskThree = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.size = 24;
		t.text = "浇水、除虫。除虫一次";
		t.textColor = 0x000000;
		t.width = 275.76;
		t.x = 80;
		t.y = 40;
		return t;
	};
	_proto.OneDays_task2_i = function () {
		var t = new eui.Label();
		this.OneDays_task2 = t;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 100;
		return t;
	};
	_proto._Label33_i = function () {
		var t = new eui.Label();
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 100;
		return t;
	};
	_proto.DrillThree_i = function () {
		var t = new eui.Image();
		this.DrillThree = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = 30;
		return t;
	};
	_proto.AwardFour_i = function () {
		var t = new eui.Image();
		this.AwardFour = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 70;
		t.source = "shovel@3x_png";
		t.width = 70;
		t.x = 210;
		t.y = 230;
		return t;
	};
	_proto.AwardThree_i = function () {
		var t = new eui.Image();
		this.AwardThree = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 70;
		t.source = "shifei_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.AwardTow_i = function () {
		var t = new eui.Image();
		this.AwardTow = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.get_i = function () {
		var t = new eui.Button();
		this.get = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.OneDays_task1_i = function () {
		var t = new eui.Label();
		this.OneDays_task1 = t;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 46;
		return t;
	};
	_proto._Label34_i = function () {
		var t = new eui.Label();
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 46;
		return t;
	};
	_proto._Image152_i = function () {
		var t = new eui.Image();
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.source = "Reward_items_png";
		t.width = 120;
		t.x = 13.81;
		t.y = 130.54;
		return t;
	};
	_proto.day0_i = function () {
		var t = new eui.Group();
		this.day0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 333;
		t.visible = false;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this.DrillOne0_i(),this.TaskOne0_i(),this.TowDays_task1_i(),this._Label35_i(),this.TaskThree0_i(),this.TowDays_task2_i(),this._Label36_i(),this.DrillThree0_i(),this.AwardFour0_i(),this.AwardTow0_i(),this.AwardThree0_i(),this.get0_i()];
		return t;
	};
	_proto.DrillOne0_i = function () {
		var t = new eui.Image();
		this.DrillOne0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -50;
		return t;
	};
	_proto.TaskOne0_i = function () {
		var t = new eui.Label();
		this.TaskOne0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "收取一次植物";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = -40;
		return t;
	};
	_proto.TowDays_task1_i = function () {
		var t = new eui.Label();
		this.TowDays_task1 = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 373.94;
		t.y = -40;
		return t;
	};
	_proto._Label35_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 393.94;
		t.y = -40;
		return t;
	};
	_proto.TaskThree0_i = function () {
		var t = new eui.Label();
		this.TaskThree0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "施肥两次";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = 40;
		return t;
	};
	_proto.TowDays_task2_i = function () {
		var t = new eui.Label();
		this.TowDays_task2 = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 100;
		return t;
	};
	_proto._Label36_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/2";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 100;
		return t;
	};
	_proto.DrillThree0_i = function () {
		var t = new eui.Image();
		this.DrillThree0 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = 30;
		return t;
	};
	_proto.AwardFour0_i = function () {
		var t = new eui.Image();
		this.AwardFour0 = t;
		t.height = 70;
		t.source = "shovel@3x_png";
		t.width = 70;
		t.x = 210;
		t.y = 230;
		return t;
	};
	_proto.AwardTow0_i = function () {
		var t = new eui.Image();
		this.AwardTow0 = t;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.AwardThree0_i = function () {
		var t = new eui.Image();
		this.AwardThree0 = t;
		t.height = 70;
		t.source = "ChemicalFertilizer1_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.get0_i = function () {
		var t = new eui.Button();
		this.get0 = t;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.day1_i = function () {
		var t = new eui.Group();
		this.day1 = t;
		t.height = 333;
		t.visible = false;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this.TaskThree1_i(),this.ThreeDays_task_i(),this._Label37_i(),this.DrillThree1_i(),this.AwardFour1_i(),this.AwardThree1_i(),this.AwardTow1_i(),this.get1_i()];
		return t;
	};
	_proto.TaskThree1_i = function () {
		var t = new eui.Label();
		this.TaskThree1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.size = 24;
		t.text = "将等级提升至中农";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = 0;
		return t;
	};
	_proto.ThreeDays_task_i = function () {
		var t = new eui.Label();
		this.ThreeDays_task = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 75;
		return t;
	};
	_proto._Label37_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 75;
		return t;
	};
	_proto.DrillThree1_i = function () {
		var t = new eui.Image();
		this.DrillThree1 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -10;
		return t;
	};
	_proto.AwardFour1_i = function () {
		var t = new eui.Image();
		this.AwardFour1 = t;
		t.height = 70;
		t.source = "shovel@3x_png";
		t.width = 70;
		t.x = 210;
		t.y = 230;
		return t;
	};
	_proto.AwardThree1_i = function () {
		var t = new eui.Image();
		this.AwardThree1 = t;
		t.height = 70;
		t.source = "ChemicalFertilizer1_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.AwardTow1_i = function () {
		var t = new eui.Image();
		this.AwardTow1 = t;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.get1_i = function () {
		var t = new eui.Button();
		this.get1 = t;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.day2_i = function () {
		var t = new eui.Group();
		this.day2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 333;
		t.visible = false;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this.leij_i(),this.FourDays_task_i(),this._Label38_i(),this.DrillTow2_i(),this.AwardFour2_i(),this.AwardThree2_i(),this.AwardTow2_i(),this.get2_i()];
		return t;
	};
	_proto.leij_i = function () {
		var t = new eui.Label();
		this.leij = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "累计种5次植物";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = 40;
		return t;
	};
	_proto.FourDays_task_i = function () {
		var t = new eui.Label();
		this.FourDays_task = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 100;
		return t;
	};
	_proto._Label38_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/5";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 100;
		return t;
	};
	_proto.DrillTow2_i = function () {
		var t = new eui.Image();
		this.DrillTow2 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -10;
		return t;
	};
	_proto.AwardFour2_i = function () {
		var t = new eui.Image();
		this.AwardFour2 = t;
		t.height = 70;
		t.source = "shovel@3x_png";
		t.width = 70;
		t.x = 210;
		t.y = 230;
		return t;
	};
	_proto.AwardThree2_i = function () {
		var t = new eui.Image();
		this.AwardThree2 = t;
		t.height = 70;
		t.source = "ChemicalFertilizer1_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.AwardTow2_i = function () {
		var t = new eui.Image();
		this.AwardTow2 = t;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.get2_i = function () {
		var t = new eui.Button();
		this.get2 = t;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.day3_i = function () {
		var t = new eui.Group();
		this.day3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 333;
		t.visible = false;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this.FiveDays_task_i(),this._Label39_i(),this.jiao_i(),this.DrillTow3_i(),this.AwardThree3_i(),this.AwardTow3_i(),this.get3_i()];
		return t;
	};
	_proto.FiveDays_task_i = function () {
		var t = new eui.Label();
		this.FiveDays_task = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 100;
		return t;
	};
	_proto._Label39_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/10";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 100;
		return t;
	};
	_proto.jiao_i = function () {
		var t = new eui.Label();
		this.jiao = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "浇水、除虫、除草共10次";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = -40;
		return t;
	};
	_proto.DrillTow3_i = function () {
		var t = new eui.Image();
		this.DrillTow3 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -10;
		return t;
	};
	_proto.AwardThree3_i = function () {
		var t = new eui.Image();
		this.AwardThree3 = t;
		t.height = 70;
		t.source = "ChemicalFertilizer1_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.AwardTow3_i = function () {
		var t = new eui.Image();
		this.AwardTow3 = t;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.get3_i = function () {
		var t = new eui.Button();
		this.get3 = t;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.day4_i = function () {
		var t = new eui.Group();
		this.day4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 333;
		t.visible = false;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this.SixDays_task1_i(),this._Label40_i(),this.DrillOne4_i(),this.TaskOne4_i(),this.TaskThree4_i(),this.SixDays_task2_i(),this._Label41_i(),this.DrillThree4_i(),this.AwardFour4_i(),this.AwardThree4_i(),this.AwardTow4_i(),this.get4_i()];
		return t;
	};
	_proto.SixDays_task1_i = function () {
		var t = new eui.Label();
		this.SixDays_task1 = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 46;
		return t;
	};
	_proto._Label40_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 46;
		return t;
	};
	_proto.DrillOne4_i = function () {
		var t = new eui.Image();
		this.DrillOne4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -50;
		return t;
	};
	_proto.TaskOne4_i = function () {
		var t = new eui.Label();
		this.TaskOne4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "买条狗";
		t.textColor = 0x000000;
		t.width = 130;
		t.x = 80;
		t.y = -40;
		return t;
	};
	_proto.TaskThree4_i = function () {
		var t = new eui.Label();
		this.TaskThree4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "使用一次道具";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = 40;
		return t;
	};
	_proto.SixDays_task2_i = function () {
		var t = new eui.Label();
		this.SixDays_task2 = t;
		t.size = 24;
		t.text = "0";
		t.textColor = 0x000000;
		t.x = 480;
		t.y = 100;
		return t;
	};
	_proto._Label41_i = function () {
		var t = new eui.Label();
		t.size = 24;
		t.text = "/1";
		t.textColor = 0x000000;
		t.x = 500;
		t.y = 100;
		return t;
	};
	_proto.DrillThree4_i = function () {
		var t = new eui.Image();
		this.DrillThree4 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = 30;
		return t;
	};
	_proto.AwardFour4_i = function () {
		var t = new eui.Image();
		this.AwardFour4 = t;
		t.height = 70;
		t.source = "shovel@3x_png";
		t.width = 70;
		t.x = 210;
		t.y = 230;
		return t;
	};
	_proto.AwardThree4_i = function () {
		var t = new eui.Image();
		this.AwardThree4 = t;
		t.height = 70;
		t.source = "ChemicalFertilizer1_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.AwardTow4_i = function () {
		var t = new eui.Image();
		this.AwardTow4 = t;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.get4_i = function () {
		var t = new eui.Button();
		this.get4 = t;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.day5_i = function () {
		var t = new eui.Group();
		this.day5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 333;
		t.visible = false;
		t.width = 450;
		t.x = 25;
		t.y = 467;
		t.elementsContent = [this.DrillOne5_i(),this.TaskOne5_i(),this.TaskThree5_i(),this.DrillThree5_i(),this.AwardFour5_i(),this.AwardThree5_i(),this.AwardTow5_i(),this.get5_i()];
		return t;
	};
	_proto.DrillOne5_i = function () {
		var t = new eui.Image();
		this.DrillOne5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "oneB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = -50;
		return t;
	};
	_proto.TaskOne5_i = function () {
		var t = new eui.Label();
		this.TaskOne5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "有一只猪";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = -40;
		return t;
	};
	_proto.TaskThree5_i = function () {
		var t = new eui.Label();
		this.TaskThree5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.bold = true;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.size = 24;
		t.text = "用猪抢好友家的农作物";
		t.textColor = 0x000000;
		t.width = 400;
		t.x = 80;
		t.y = 40;
		return t;
	};
	_proto.DrillThree5_i = function () {
		var t = new eui.Image();
		this.DrillThree5 = t;
		t.anchorOffsetX = 0;
		t.anchorOffsetY = 0;
		t.height = 40;
		t.scaleX = 1;
		t.scaleY = 1;
		t.source = "twoB@3x_png";
		t.width = 40;
		t.x = 27;
		t.y = 30;
		return t;
	};
	_proto.AwardFour5_i = function () {
		var t = new eui.Image();
		this.AwardFour5 = t;
		t.height = 70;
		t.source = "shovel@3x_png";
		t.width = 70;
		t.x = 210;
		t.y = 230;
		return t;
	};
	_proto.AwardThree5_i = function () {
		var t = new eui.Image();
		this.AwardThree5 = t;
		t.height = 70;
		t.source = "shifei_png";
		t.width = 70;
		t.x = 120;
		t.y = 230;
		return t;
	};
	_proto.AwardTow5_i = function () {
		var t = new eui.Image();
		this.AwardTow5 = t;
		t.height = 70;
		t.source = "manure@3x_png";
		t.width = 70;
		t.x = 30;
		t.y = 230;
		return t;
	};
	_proto.get5_i = function () {
		var t = new eui.Button();
		this.get5 = t;
		t.enabled = false;
		t.height = 34;
		t.label = "领取";
		t.scaleX = 1;
		t.scaleY = 1;
		t.width = 90;
		t.x = 300;
		t.y = 246;
		return t;
	};
	_proto.OneDays_i = function () {
		var t = new eui.Image();
		this.OneDays = t;
		t.height = 40;
		t.source = "one@3x_png";
		t.width = 60;
		t.x = 40.32;
		t.y = 365;
		return t;
	};
	_proto.TowDays_i = function () {
		var t = new eui.Image();
		this.TowDays = t;
		t.height = 36;
		t.source = "twoT@3x_png";
		t.width = 60;
		t.x = 100.03;
		t.y = 365;
		return t;
	};
	_proto.ThreeDays_i = function () {
		var t = new eui.Image();
		this.ThreeDays = t;
		t.height = 36;
		t.source = "threeT@3x_png";
		t.width = 60;
		t.x = 159.68;
		t.y = 365;
		return t;
	};
	_proto.FourDays_i = function () {
		var t = new eui.Image();
		this.FourDays = t;
		t.height = 36;
		t.source = "fourT@3x_png";
		t.width = 60;
		t.x = 219.88;
		t.y = 365;
		return t;
	};
	_proto.FiveDays_i = function () {
		var t = new eui.Image();
		this.FiveDays = t;
		t.height = 36;
		t.source = "fiveJ@3x_png";
		t.width = 60;
		t.x = 279.62;
		t.y = 365;
		return t;
	};
	_proto.SixDays_i = function () {
		var t = new eui.Image();
		this.SixDays = t;
		t.height = 36;
		t.source = "sixT@3x_png";
		t.width = 60;
		t.x = 339.29;
		t.y = 365;
		return t;
	};
	_proto.SevenDays_i = function () {
		var t = new eui.Image();
		this.SevenDays = t;
		t.height = 36;
		t.source = "sevenT@3x_png";
		t.width = 60;
		t.x = 398.05;
		t.y = 365;
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