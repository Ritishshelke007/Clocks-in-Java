import java.applet.*;
import java.awt.*;
import java.util.*;
import java.text.*;

public class project_final extends Applet{
Font f1,f2;
AudioClip clip;
	
	@Override
	public void init(){
		setBackground(Color.BLACK);
		clip = getAudioClip(getDocumentBase(), "clock_sound.wav");
		new Thread() {
			@Override
			public void run()
			{
				try{
				while (true) {
					repaint();
					Thread.sleep(1000);
				}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}

	/* private void delayAnimation()
	{
		try {
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/

public void display(int val, int pos, Graphics g)
	{

		// lit-up the segment

		// 0-th segment
		if ((val & 1) != 0)
			g.fillRect(pos, 400, 7, 50);

		// 1-st segment
		if ((val & 2) != 0)
			g.fillRect(pos, 395, 50, 7);

		// 2-nd segment
		if ((val & 4) != 0)
			g.fillRect(pos + 45, 400, 7, 50);

		// 3-rd segment
		if ((val & 8) != 0)
			g.fillRect(pos + 45, 450, 7, 50);

		// 4-th segment
		if ((val & 16) != 0)
			g.fillRect(pos, 500, 50, 7);

		// 5-th segment
		if ((val & 32) != 0)
			g.fillRect(pos, 450, 7, 50);

		// 6-th segment
		if ((val & 64) != 0)
			g.fillRect(pos + 5, 450, 40, 7);
	}

	public void paint(Graphics g){

		clip.play();
		clip.stop();
		double angle;
		int x,y;

		//draw numbers
		g.setColor(new Color(255,255,255));
		g.setFont(f1);

		for(int i=1;i<=12;i++){
			angle=i*Math.PI/6;
			x=(int)(200*Math.sin(angle));
			y=(int)(200*Math.cos(angle));
			g.drawString(Integer.toString(i),300+x,300-y);
		}
		g.fillOval(295,295,10,10);

		//get calendar instance
		DateFormat Date = DateFormat.getDateInstance();
		Calendar cal=Calendar.getInstance();

		TimeZone tz = cal.getTimeZone();
		String s = tz.getDisplayName();
		String currentDate = Date.format(cal.getTime());

		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int min=cal.get(Calendar.MINUTE);
		int sec=cal.get(Calendar.SECOND);

		hour=hour%12;
		// hour hand
		angle=(hour*Math.PI/6)+
				(min*Math.PI/(6*60))+
				(sec*Math.PI/(360*60));
		x=(int)(160*Math.sin(angle));
		y=(int)(160*Math.cos(angle));
		g.drawLine(300,300,300+x,300-y);

		//minute hand
		angle=(min*Math.PI/30)+
				(sec*Math.PI/(30*60));
		x=(int)(190*Math.sin(angle));
		y=(int)(190*Math.cos(angle));
		g.drawLine(300,300,300+x,300-y);

        g.setColor(new Color(255,0,0));

		angle=(sec*Math.PI/(30));
		x=(int)(190*Math.sin(angle));
		y=(int)(190*Math.cos(angle));
		g.drawLine(300,300,300+x,300-y);

		 f1 = new Font("Arial",Font.BOLD,26); 
		g.setFont(f1);
		g.setColor(Color.cyan);
		g.drawString("Analog Clock Showing Current Time",100,700);
		g.drawString("Digital Clock Showing Current Date and Time",1050,700);
		int[] digits
		= new int[] { 63, 12, 118,
					94, 77, 91,
					123, 14, 127,
					95 };

		// Get the system current time	
	int hour1 = cal.get(Calendar.HOUR_OF_DAY);
	int minute = cal.get(Calendar.MINUTE);
	int second = cal.get(Calendar.SECOND);

	// Deciding AM/PM
	int am = 1;
	if (hour1 > 12) {
		hour1 -= 12;
		am = 0;
	}

		// Display hour
		g.setColor(Color.cyan);
		// tens digit
		display(digits[hour / 10], 1000, g);
		// units digit
		display(digits[hour % 10], 1075, g);

		// Display minute

		// tens digit
		display(digits[minute / 10], 1175, g);
		// units digit
		display(digits[minute % 10], 1250, g);

		// Display second

		// tens digit
		display(digits[second / 10], 1350, g);
		// units digit
		display(digits[second % 10], 1425, g);

		// Display AM/PM
	
		if (am == 1){
			g.setFont(f1);
			g.drawString("AM", 1500, 500);}
		else
			g.drawString("PM", 1500, 500);


			g.setColor(Color.orange);
			g.drawString(s, 1500, 580);
			g.drawString(currentDate, 1500, 620);

			g.setColor(Color.cyan);
			// Display ratio signs
			g.fillRect(1150, 425, 5, 5);
			g.fillRect(1150, 475, 5, 5);
	
			g.fillRect(1325, 425, 5, 5);
			g.fillRect(1325, 475, 5, 5); 

		f2 = new Font("Verdana",Font.ITALIC,20); 
		g.setColor(Color.white);
		g.setFont(f2);
		g.drawString("Current Date and Time in Java Applet", 1400,900);
}
	}

/*
<applet code = "project_final" width = 1900 height = 980>
</applet>
*/