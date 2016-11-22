var __interpretation_started_timestamp__;
var pi = 3.1415926535897931;
var alpha;

var main = function()
{
	__interpretation_started_timestamp__ = Date.now();

	brick.motor(M1).setPower(100);
	script.wait(10);
	while (true) {
		alpha = Math.atan2(brick.accelerometer().read()[0], brick.accelerometer().read()[2]) * 180 / pi;
		brick.display().addLabel(alpha, 1, 1);
		brick.display().redraw();
		brick.motor(M3).setPower(alpha);
		if (brick.keys().wasPressed(KeysEnum.Left) == 1) {
			script.system("killall trikGui");
		}
	}
}