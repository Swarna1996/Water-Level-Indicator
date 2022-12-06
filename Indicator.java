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
class WaterTankWindow extends JFrame{
	private AlarmWindow alarmWindow;
	private DisplayWindow displayWindow;
	private SplitterWindow splitterWindow;
	
	private JSlider waterLevelSlider;
	WaterTankWindow(){
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("WaterTank Window");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		
		waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);
		waterLevelSlider.setFont(new Font("",1,25));
		waterLevelSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int waterLevel=waterLevelSlider.getValue();
				alarmWindow.operateAlarm(waterLevel);
				displayWindow.displayWaterLevel(waterLevel);
				splitterWindow.split(waterLevel); 
			}
		});
		add(waterLevelSlider);
	}
	public void addAlarmWindow(AlarmWindow alarmWindow){
		this.alarmWindow=alarmWindow;
	}
	public void addSplitterWindow(SplitterWindow splitterWindow){
		this.splitterWindow=splitterWindow;
	}
	public void addDisplayWindow(DisplayWindow displayWindow){
		this.displayWindow=displayWindow;
	}
}

class Indicator{ 
	public static void main(String args[]){   
		WaterTankWindow wt = new WaterTankWindow();
		wt.addAlarmWindow(new AlarmWindow());
		wt.addDisplayWindow(new DisplayWindow());
		wt.addSplitterWindow(new SplitterWindow());
		wt.setVisible(true);
	} 
}
