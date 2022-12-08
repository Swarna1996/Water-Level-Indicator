import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class DisplayWindow extends JFrame{
	private JLabel displayLabel;
	DisplayWindow(){
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Display Window");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		//-----------------------------------------
		displayLabel=new JLabel("0");
		displayLabel.setFont(new Font("",1,25));
		add(displayLabel);
		setVisible(true);
	}
	public void displayWaterLevel(int waterLevel){
		displayLabel.setText(waterLevel+""); //Integer.toString(waterLevel)
	}
}
class AlarmWindow extends JFrame{
	private JLabel alarmLabel;
	AlarmWindow(){
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Alarm Window");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		//-----------------------------------------
		alarmLabel=new JLabel("OFF");
		alarmLabel.setFont(new Font("",1,25));
		add(alarmLabel);
		setVisible(true);
	}
	public void operateAlarm(int waterLevel){
		alarmLabel.setText(waterLevel>=50 ? "ON":"OFF");
	}
}
class SplitterWindow extends JFrame{
	private JLabel splitterLabel;
	SplitterWindow(){
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Splitter Window");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		//-----------------------------------------
		splitterLabel=new JLabel("OFF");
		splitterLabel.setFont(new Font("",1,25));
		add(splitterLabel);
		setVisible(true);
	}
	public void split(int waterLevel){
		splitterLabel.setText(waterLevel>=75 ? "Splitter ON":"Splitter OFF");
	}
}

class SMSWindow extends JFrame{
	private JLabel SMSLabel;
	SMSWindow(){
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("SMS Window");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		//-----------------------------------------
		SMSLabel=new JLabel("OFF");
		SMSLabel.setFont(new Font("",1,25));
		add(SMSLabel);
		setVisible(true);
	}
	public void sentSMS(int waterLevel){
		SMSLabel.setText("Sending SMS : "+waterLevel);
	}
}

class WaterTankWindow extends JFrame{
	private WaterTankController waterTankController;
	
	private JSlider waterLevelSlider;
	WaterTankWindow(WaterTankController waterTankController){
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("WaterTank Window");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		this.waterTankController=waterTankController;
		
		waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);
		waterLevelSlider.setFont(new Font("",1,25));
		waterLevelSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int waterLevel=waterLevelSlider.getValue();
				waterTankController.setWaterLevel(waterLevel);
			}
		});
		add(waterLevelSlider);
	}
}

class WaterTankController{
	private AlarmWindow alarmWindow;
	private DisplayWindow displayWindow;
	private SplitterWindow splitterWindow;
	private SMSWindow smsWindow;
	
	private int waterLevel;
	
	public void addAlarmWindow(AlarmWindow alarmWindow){
		this.alarmWindow=alarmWindow;
	}
	public void addSplitterWindow(SplitterWindow splitterWindow){
		this.splitterWindow=splitterWindow;
	}
	public void addDisplayWindow(DisplayWindow displayWindow){
		this.displayWindow=displayWindow;
	}
	public void addSMSWindow(SMSWindow smsWindow){
		this.smsWindow=smsWindow;
	}
	public void setWaterLevel(int waterLevel){
		if(this.waterLevel!=waterLevel){
			alarmWindow.operateAlarm(waterLevel);
			displayWindow.displayWaterLevel(waterLevel);
			splitterWindow.split(waterLevel); 
			smsWindow.sentSMS(waterLevel);
		}
	}
}

class Indicator{ 
	public static void main(String args[]){   
		WaterTankController waterTankController = new WaterTankController();
		waterTankController.addAlarmWindow(new AlarmWindow());
		waterTankController.addSplitterWindow(new SplitterWindow());
		waterTankController.addDisplayWindow(new DisplayWindow());
		waterTankController.addSMSWindow(new SMSWindow());
		
		new WaterTankWindow(waterTankController).setVisible(true);
	} 
}
