import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import view.Publisher;

import calendar.CalendarFilter;
import calendar.ConflictFilter;
import calendar.KeywordFilter;
import calendar.NativeFilter;
import calendar.Sorter;
import calendar.TimeFilter;
import calendar.XMLCal;
import event.Event;



public class BrowserViewer extends JFrame{
	
	protected Calendar before = new GregorianCalendar();
	protected Calendar after = new GregorianCalendar();
    public static final Dimension SIZE = new Dimension(400, 300);

	protected ArrayList<File> files= new ArrayList<File>();
	protected TivooSystem myModel;
	protected XMLCal calendar ;
    protected String myTitle;
    protected static final JFileChooser ourChooser = new JFileChooser("./resources");
    protected JTextField myMessage;
    protected static final Font ourFont = new Font("SansSerif", Font.BOLD, 12);
    protected static final Font ourFixedFont = new Font("Monospaced", Font.BOLD, 12);
    protected String[] filterWords;
    protected JCheckBox checkbox1;
    protected JCheckBox checkbox2;
    protected JRadioButton radioButton1;
    protected JRadioButton radioButton2;
    protected JRadioButton radioButton3;
    protected JRadioButton radioButton4;
    protected JRadioButton radioButton5;
    protected JRadioButton radioButton6;
    protected JRadioButton radioButton7;
    protected JRadioButton radioButton8;
    protected JRadioButton radioButton9;
    protected JRadioButton radioButton10;

    protected JCheckBox checkbox3;
    protected JCheckBox checkbox4;
    protected JCheckBox checkbox5;
    protected JButton summaryButton;
    private JEditorPane myPage;

    protected boolean enableKeywordFilter;
    protected boolean enableTimeFilter;
    protected boolean enableShowFunction;
    protected boolean reverse;
    protected boolean enableConflict;
    protected int selectFilterType;
    protected int selectSortType;
    protected int selectShowType;
    
    
	 public BrowserViewer(String title, TivooSystem model) {
		 
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setModel(model);
	        JPanel panel = (JPanel) getContentPane();
	        panel.setLayout(new BorderLayout());
	        setTitle(title);
	        myTitle = title;
	        makeMenus();
	         checkbox1 = new JCheckBox("keyword");
	        panel.add(checkbox1, BorderLayout.WEST);
	        checkbox1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    	enableKeywordFilter = checkbox1.isSelected();
                }

            }); 
	        panel.add(makeKeywordFilter(), BorderLayout.CENTER);
	        JPanel panel2= new JPanel();
	        panel.add(panel2,BorderLayout.SOUTH);
	        panel2.setLayout(new BorderLayout());
	        checkbox2 = new JCheckBox("time      ");
	        panel2.add(checkbox2, BorderLayout.WEST);
	        checkbox2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    	enableTimeFilter = checkbox2.isSelected();
                }

            }); 
	        panel2.add(makeTimeFilter(), BorderLayout.CENTER);
	        JPanel panel3= new JPanel();
	        panel2.add(panel3,BorderLayout.SOUTH);
	        panel3.setLayout(new BorderLayout());
	        checkbox4 = new JCheckBox("Show event in one day/week/month");
	        panel3.add(checkbox4,BorderLayout.WEST);
	        checkbox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableShowFunction=checkbox4.isSelected();
            }

            }); 
	        panel3.add(showInDayWeekYear(),BorderLayout.CENTER);
            JPanel panel4 = new JPanel();
	        panel3.add(panel4,BorderLayout.SOUTH);
	        panel4.setLayout(new BorderLayout());

	        
	        panel4.add(makeSort(),BorderLayout.CENTER);
	        summaryButton = new JButton("Show");
	        panel4.add(summaryButton, BorderLayout.EAST);
	        summaryButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	try {
						process();
						makePublish();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }


        }); 
	        panel4.add(makePageDisplay(), BorderLayout.SOUTH);

	        pack();
	        setVisible(true);
	    }
	 
	 
		private JPanel showInDayWeekYear() {
			 JPanel p = new JPanel();
		        BoxLayout layout = new BoxLayout(p, BoxLayout.PAGE_AXIS);
		        p.setLayout(layout);

		        ShowPanel panel = new ShowPanel();
		        p.add(panel);
		        p.add(Box.createRigidArea(new Dimension(100, 5)));
		        
		        panel.setEnabled(true);
		        p.setBorder(BorderFactory.createTitledBorder("Show"));
		        return p;
	}
	    protected class ShowPanel extends JPanel {
	    	public ShowPanel(){
	    		BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
	            setLayout(layout);
	            JLabel keyWordLabel = new JLabel("Show Type:");
	            keyWordLabel.setFont(ourFixedFont);
	            keyWordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	            this.add(keyWordLabel);
	            this.add(Box.createRigidArea(new Dimension(10, 0)));
	            
	            radioButton8 = new JRadioButton("day");
	            radioButton9 = new JRadioButton("week");
	            radioButton10 = new JRadioButton("month");
	            
	            this.add(radioButton8);
	            this.add(radioButton9);
	            this.add(radioButton10);

	            ButtonGroup group = new ButtonGroup();
	        
	            group.add(radioButton8);
	            group.add(radioButton9);
	            group.add(radioButton10);

	            radioButton8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    	if(radioButton8.isSelected())
                    		selectShowType=0;
                }

            }); 
	            radioButton2.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    	if(radioButton9.isSelected())
	                    		selectShowType=1;

	                }

	            }); 
	            radioButton3.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    	if(radioButton10.isSelected())
	                    		selectShowType=2;

	                }

	            }); 

	    	}
	    	
	    	
	    	
	    }
protected class KeywordFilterPanel extends JPanel {
	        
	        private JTextField myGuessField;

	        public KeywordFilterPanel() {

	            myGuessField = new JTextField(10);
	            myGuessField.setFont(ourFont);
	            myGuessField.setEnabled(true);

	            
	            myGuessField.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    sendToModel(e.getActionCommand());
	                    
	                }

	            }); 


	            BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
	            setLayout(layout);
	            JLabel keyWordLabel = new JLabel("Keywords:");
	            keyWordLabel.setFont(ourFixedFont);
	            keyWordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	            this.add(keyWordLabel);
	            keyWordLabel.setLabelFor(myGuessField);
	            this.add(Box.createRigidArea(new Dimension(10, 0)));
	            this.add(myGuessField);
	            
	            radioButton1 = new JRadioButton("title");
	            radioButton2 = new JRadioButton("description");
	            radioButton3 = new JRadioButton("author");
	            radioButton4 = new JRadioButton("actor");
	            
	            this.add(radioButton1);
	            this.add(radioButton2);
	            this.add(radioButton3);
	            this.add(radioButton4);

	            ButtonGroup group = new ButtonGroup();
	        
	            group.add(radioButton1);
	            group.add(radioButton2);
	            group.add(radioButton3);
	            group.add(radioButton4);

	            radioButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    	if(radioButton1.isSelected())
                    		selectFilterType=0;
                }

            }); 
	            radioButton2.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    	if(radioButton2.isSelected())
	                    		selectFilterType=1;

	                }

	            }); 
	            radioButton3.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    	if(radioButton3.isSelected())
	                    		selectFilterType=2;

	                }

	            }); 
	            radioButton4.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    	if(radioButton4.isSelected())
	                    		selectFilterType=3;

	                }

	            }); 
	        }
	    }

		private JComponent makePageDisplay ()
		{
	        // displays the web page
	        myPage = new JEditorPane();
	        myPage.setPreferredSize(SIZE);
	        // allow editor to respond to link-clicks/mouse-overs
	        myPage.setEditable(false);
	        myPage.addHyperlinkListener(new LinkFollower());
			return new JScrollPane(myPage);
		}
		
	    private class LinkFollower implements HyperlinkListener
	    {
	        public void hyperlinkUpdate (HyperlinkEvent evt)
	        {
	            // user clicked a link, load it and show it
	            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
	            {
	            	update(evt.getURL().toString());
	            }
	            }
	    }
	        
	    private void update (String url)
	    {
	        try
	        {
	            myPage.setPage(url);
	        }
	        catch (IOException e)
	        {
	        	// should never happen since only checked URLs make it this far ...
	            showError("Could not load " + url);
	        }
	    }
	    
	 private void process() throws NoSuchAlgorithmException, ParseException, IOException 
	 {
		 calendar = myModel.parse(files);
	     if(enableKeywordFilter)
	     {
    		 KeywordFilter keywordfilter = new KeywordFilter(calendar);

	    	 switch (selectFilterType)
	    	 {
	    	 case 0:
	    		 keywordfilter.filterTitle(filterWords);
	    		 break;
	    	 case 1:
	    		 keywordfilter.filterDescription(filterWords);
	    		 break;
	    	 case 2:
	    		 keywordfilter.filterAuthor(filterWords);
	    		 break; 
	    	 case 3:
	    		 NativeFilter nativefilter = new NativeFilter(calendar);
	    		 nativefilter.filterActor(filterWords);
	    		 break;
	    	 }
	     }
	     if(enableTimeFilter)
	     {
	    	 TimeFilter timefilter = new TimeFilter(calendar);
	    	 timefilter.filterTime(before, after);
	     }
	     Sorter sort = new Sorter(calendar);

         if(enableShowFunction)
         {
        	 CalendarFilter calendarfilter = new CalendarFilter(calendar);
        	 switch (selectShowType)
	    	 {
	    	 case 0:
	    		 calendarfilter.filterDay(before);
	    		 break;
	    	 case 1:
	    		 calendarfilter.filterWeek(before);
	    		 break;
	    	 case 2:
	    		 calendarfilter.filterMonth(before);
	    		 break; 

	    	 }
         }
        	 
        	 
        	 
        	 
	     switch (selectSortType)
    	 {

    	 case 0:
    		 sort.sortByTitle();
    		 break;
    	 case 1:
    		 sort.sortByStart();
    		 break;
    	 case 2:
    		 sort.sortByEnd();
    		 break; 
    	 
    		}
	     if(reverse)
	    	 sort.reverse();
	     if(enableConflict)
	     {
	    	 ConflictFilter conflict = new ConflictFilter(calendar);
	    	 conflict.filterConflicts();
	     }
	     

    	 }

private void makePublish() throws NoSuchAlgorithmException, IOException
{  
	Publisher publisher = new Publisher(calendar);
	 publisher.publish();
	 update("file:///Users/mac/Documents/workspace/TiVoo/resources/summary.html");
}

	
	 private JPanel makeSort()
	 {
		 JPanel p = new JPanel();
	        BoxLayout layout = new BoxLayout(p, BoxLayout.PAGE_AXIS);
	        p.setLayout(layout);
	        
	     /*   JLabel label = new JLabel("Sort type:");
	        label.setFont(ourFixedFont);
	        label.setHorizontalAlignment(SwingConstants.RIGHT); */
	       	JPanel p1 = new JPanel();
       //     p.add(label,BorderLayout.EAST);
            p.add(p1,BorderLayout.CENTER);
            p.add(Box.createRigidArea(new Dimension(10, 0)));
            
            radioButton5 = new JRadioButton("title");
            radioButton6 = new JRadioButton("start");
            radioButton7 = new JRadioButton("end");
            checkbox3 = new JCheckBox("reverse");
            checkbox5 = new JCheckBox("conflict");
            p1.add(radioButton5);
            p1.add(radioButton6);
            p1.add(radioButton7);
            p1.add(checkbox3);
            p1.add(checkbox5);


            ButtonGroup group = new ButtonGroup();

            group.add(radioButton5);
            group.add(radioButton6);
            group.add(radioButton7);

            radioButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	if(radioButton5.isSelected())
                		selectSortType=0;
            }

        }); 
            radioButton6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    	if(radioButton6.isSelected())
                    		selectSortType=1;

                }

            }); 
            radioButton7.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    	if(radioButton7.isSelected())
                    		selectSortType=2;

                }

            }); 
            checkbox3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    		reverse=checkbox3.isSelected();

                }

            }); 
            checkbox5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	enableConflict=checkbox5.isSelected();

                }

            }); 

	        p.setBorder(BorderFactory.createTitledBorder("Sort type"));
	        return p;	
	 }
	    
	 private JPanel makeTimeFilter() {

		 JPanel p = new JPanel();
	        BoxLayout layout = new BoxLayout(p, BoxLayout.PAGE_AXIS);
	        p.setLayout(layout);

	        TimeFilterPanel panel = new TimeFilterPanel();
	        p.add(panel);
	        p.add(Box.createRigidArea(new Dimension(100, 5)));
	        
	        panel.setEnabled(true);
	        p.setBorder(BorderFactory.createTitledBorder("Filter by time"));
	        return p;	
	 }



	public JPanel makeKeywordFilter() {
	        JPanel p = new JPanel();
	        BoxLayout layout = new BoxLayout(p, BoxLayout.PAGE_AXIS);
	        p.setLayout(layout);

	        KeywordFilterPanel panel = new KeywordFilterPanel();
	        p.add(panel);
	        p.add(Box.createRigidArea(new Dimension(100, 5)));
	        
	        panel.setEnabled(true);
	        p.setBorder(BorderFactory.createTitledBorder("Filter by keyword"));
	        return p;
		
	}



	public void setModel(TivooSystem model)
	 {
		 myModel = model;
	 }
	 
	  protected void makeMenus() {
	        JMenuBar bar = new JMenuBar();
	        bar.add(makeFileMenu());
	        bar.add(makeGameMenu());
	        setJMenuBar(bar);
	    }

	    protected JMenu makeFileMenu() {
	        JMenu fileMenu = new JMenu("File");

	        fileMenu.add(new AbstractAction("Open") {
	            public void actionPerformed(ActionEvent ev) {
	                int retval = ourChooser.showOpenDialog(null);
	                if (retval == JFileChooser.APPROVE_OPTION) {
	                    File file = ourChooser.getSelectedFile();
	                    
	                    	files.add(file);
	                        myModel.parse(files);
	                        
	                    } 
	                	
	                }
	            }
	        );

	        fileMenu.add(new AbstractAction("Quit") {
	            public void actionPerformed(ActionEvent ev) {
	                System.exit(0);
	            }
	        });
	        return fileMenu;
	    }
	    
	    protected JMenu makeGameMenu() {
	        JMenu gameMenu = new JMenu("New Search");

	        gameMenu.add(new AbstractAction("Resets") {
	        	
	            public void actionPerformed(ActionEvent ev) {
	                newGame();
	            }
	        });

	        return gameMenu;
	    }
	    
	    
	        
	    protected class TimeFilterPanel extends JPanel {
	        
	        private JTextField startTimeField;
	        private JTextField endTimeField;

	        public TimeFilterPanel() {

	            startTimeField= new JTextField(10);
	            startTimeField.setFont(ourFont);
	            startTimeField.setEnabled(true);

	            
	            startTimeField.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                       try {
							setStartTime(e.getActionCommand());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }

	            }); 
	            
	            endTimeField= new JTextField(10);
	            endTimeField.setFont(ourFont);
	            endTimeField.setEnabled(true);

	            
	            endTimeField.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                       try {
							setEndTime(e.getActionCommand());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }

	            }); 

	            BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
	            setLayout(layout);
	            JLabel timeLabel1 = new JLabel("Start time:");
	            timeLabel1.setFont(ourFixedFont);
	            timeLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	            this.add(timeLabel1);
	            timeLabel1.setLabelFor(startTimeField);
	            this.add(Box.createRigidArea(new Dimension(10, 0)));
	            this.add(startTimeField);
	            
	            JLabel timeLabel2 = new JLabel("End time:");
	            timeLabel2.setFont(ourFixedFont);
	            timeLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	            this.add(timeLabel2);
	            timeLabel1.setLabelFor(endTimeField);
	            this.add(Box.createRigidArea(new Dimension(10, 0)));
	            this.add(endTimeField);
	            
	        }
	    }

	    protected void newGame() {
            myModel = new TivooSystem();

	    	files = new ArrayList<File>();
			calendar = myModel.parse(files);
	    }
	    
	    public void showMessage(String s) {
	        myMessage.setText(s);
	    }

	    public void showError(String s) {
	        JOptionPane.showMessageDialog(this, s, "Error",
	                JOptionPane.ERROR_MESSAGE);
	    }
	    
	    protected void sendToModel(String s){
	    	filterWords= s.split("&");
	    	for(String i :filterWords)
	    	System.out.println(i);
	    }
		protected void setStartTime(String s) throws ParseException {
			before.setTime(new SimpleDateFormat("yyyyMMdd").parse(s));			
		}
		protected void setEndTime(String s) throws ParseException {
			after.setTime(new SimpleDateFormat("yyyyMMdd").parse(s));			
		}
}

