package com.delorean.jeopardy;

import kankan.wheel.widget.ArrayWheelAdapter;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectQuestionActivity extends Activity {

			String wheelMenu[];
			int numberOfQuestions;

			// Wheel scrolled flag
			private boolean wheelScrolled = false;

			@Override
			public void onCreate(Bundle savedInstanceState)
				{
					super.onCreate(savedInstanceState);
					setContentView(R.layout.activity_select_question);
					Intent intent = getIntent();
					numberOfQuestions = intent.getIntExtra(HomeActivity.NUMBER_OF_QUESTIONS,0);
					
					wheelMenu = generateWheelMenuItems(numberOfQuestions);
					initWheel(R.id.select_question_wheel);				
				}

			// Wheel scrolled listener
			OnWheelScrollListener scrolledListener = new OnWheelScrollListener()
				{
					public void onScrollStarts(WheelView wheel)
						{
							wheelScrolled = true;
						}

					public void onScrollEnds(WheelView wheel)
						{
							wheelScrolled = false;
							updateStatus();
						}
				};

			// Wheel changed listener
			private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
				{
					public void onChanged(WheelView wheel, int oldValue, int newValue)
						{
							if (!wheelScrolled)
								{
									updateStatus();
								}
						}
				};

			/**
			 * Updates entered PIN status
			 */
			private void updateStatus()
				{
					//Updates when an item is selected in the view
				}

			/**
			 * Initializes wheel
			 * 
			 * @param id
			 *          the wheel widget Id
			 */

			private void initWheel(int id)
				{
					WheelView wheel = (WheelView) findViewById(id);
					wheel.setAdapter(new ArrayWheelAdapter<String>(wheelMenu));
					wheel.setVisibleItems(4);
					wheel.setCurrentItem(0);
					wheel.addChangingListener(changedListener);
					wheel.addScrollingListener(scrolledListener);
				}

			public void start (View view) {
				Intent intent = new Intent(this, RoundActivity.class);
				//Get the value from the wheel:
		    	int id = Integer.parseInt(wheelMenu[getWheel(R.id.select_question_wheel).getCurrentItem()]);
		    	
		    	Log.d(HomeActivity.LOG_TAG, "Selected qID: " + id);
		    	
		    	intent.putExtra(HomeActivity.QUESTION_ID, id);
		    	intent.putExtra(HomeActivity.NUMBER_OF_QUESTIONS, numberOfQuestions);
		    	startActivity(intent);
			}
			
			public String[] generateWheelMenuItems(int numberOfQuestions) {
				String[] items = new String[numberOfQuestions];
				for(int i=0; i < numberOfQuestions; i++) {
					items[i] = String.valueOf(i+1);
					//i+1 because of indexes in RoundActivity starts at 0
				}
				return items;
			}

			/**
			 * Returns wheel by Id
			 * 
			 * @param id
			 *          the wheel Id
			 * @return the wheel with passed Id
			 */
			private WheelView getWheel(int id)
				{
					return (WheelView) findViewById(id);
				}


		}