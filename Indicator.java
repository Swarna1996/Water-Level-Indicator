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
		//-----------------------------------------
		alarmWindow=new AlarmWindow();
		alarmWindow.setVisible(true);
		
		displayWindow=new DisplayWindow();
		displayWindow.setVisible(true);
		
		splitterWindow=new SplitterWindow();
		splitterWindow.setVisible(true);
		//-----------------------------------------
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
}

class Indicator{ 
	public static void main(String args[]){   
		new WaterTankWindow().setVisible(true);
	} 
}
