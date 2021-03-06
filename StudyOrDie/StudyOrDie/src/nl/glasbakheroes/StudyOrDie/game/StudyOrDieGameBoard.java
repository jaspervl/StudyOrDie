package nl.glasbakheroes.StudyOrDie.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.ItemWrap;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.Objects.VendingMachine;
import nl.glasbakheroes.StudyOrDie.Objects.Wall;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/**
 * The SoD Game board
 * 
 * @author Niels Jan
 */
public class StudyOrDieGameBoard extends GameBoard {

	public static final int REQUEST_COMBAT_INTENT = 1337;
	public static final int GAMEBOARD_WIDTH = 24;
	public static final int GAMEBOARD_HEIGHT = 12;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	private CoreActivity activity;
	private StudyOrDieModel model;
	private Avatar avatar;
	private LevelLoader leverloader;

	public StudyOrDieGameBoard() {
		super(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
	}

	@Override
	public void onEmptyTileClicked(int x, int y) {

	}

	/**
	 * Create a horizontal wall in the gameboard.
	 * 
	 * @param x1
	 *            The x position in the grid to start the wall.
	 * @param x2
	 *            The x position in the grid to end the wall.
	 * @param y
	 *            The y position in the grid to place the wall.
	 */
	public void createWallHorizontal(int x1, int x2, int y) {
		int max = 0;
		int min = 0;
		if (x1 > x2) {
			max = x1;
			min = x2;
		} else {
			max = x2;
			min = x1;
		}
		for (; min <= max; min++) {
			addGameObject(new Wall("Horizontal"), min, y);
		}
	}

	/**
	 * Create a vertical wall in the gameboard.
	 * 
	 * @param y1
	 *            The y position in the grid to start the wall.
	 * @param y2
	 *            The y position in the grid to end the wall.
	 * @param x
	 *            The x position in the grid to place the wall.
	 */
	public void createWallVertical(int y1, int y2, int x) {
		int max = 0;
		int min = 0;
		if (y1 > y2) {
			max = y1;
			min = y2;
		} else {
			max = y2;
			min = y1;
		}
		for (; min <= max; min++) {
			addGameObject(new Wall("Vertical"), x, min);
		}
	}

	/**
	 * Create four corners of wall, it can only be a 'rectangle'
	 * 
	 * @param x1
	 *            The lowest x value [0-23]
	 * @param x2
	 *            The highest x value [0-23]
	 * @param y1
	 *            The lowest y value [0-11]
	 * @param y2
	 *            The highest y value [0-11]
	 */
	public void createWallCorners(int x1, int x2, int y1, int y2) {

		addGameObject(new Wall("NW"), x1, y1);
		addGameObject(new Wall("SW"), x1, y2);
		addGameObject(new Wall("SE"), x2, y2);
		addGameObject(new Wall("NE"), x2, y1);
	}

	/**
	 * Method to move the avatar.
	 * 
	 * @param direction
	 *            The direction the avatar wants to move. [Up / Down / Left /
	 *            Right]
	 */
	public void moveAvatar(int direction) {
		/*
		 * Checks whether the avatar is too close to boundaries and other
		 * objects
		 */
		if (checkBoundaries(direction)) {
			/* Set the orientation of the avatar image in a certain direction */
			avatar.setImage(direction);
			/* Move the avatar in a certain direction */
			switch (direction) {
			case UP:
				moveObject(avatar, avatar.getPositionX(),
						avatar.getPositionY() - 1);
				model.addStep();
				break;
			case DOWN:
				moveObject(avatar, avatar.getPositionX(),
						avatar.getPositionY() + 1);
				model.addStep();
				break;
			case LEFT:
				moveObject(avatar, avatar.getPositionX() - 1,
						avatar.getPositionY());
				model.addStep();
				break;
			case RIGHT:
				moveObject(avatar, avatar.getPositionX() + 1,
						avatar.getPositionY());
				model.addStep();
				break;
			default:
				break;
			}

			if (model.fightRandomBoss()) {
				model.saveGame();
				model.randomEncounterOccured();
				/* Save the current location of the avatar in the level loader */
				Log.w("Loader", "Last avatar position: "
						+ model.getAvatar().getPositionX() + ", "
						+ model.getAvatar().getPositionY());
				/* Create a new boss and fetch it from the model */
				model.addBoss("Random", 50, 0);
				Boss randomBoss = model.findRandomBoss();
				/* Start new combat activity */
				Intent randomCombatIntent = new Intent(activity,
						CombatActivity.class);
				randomCombatIntent.putExtra("bossName", randomBoss.getName());
				activity.startActivity(randomCombatIntent);
			}
			updateView();
		}

	}

	/**
	 * Helper method, check wheather the desired movement is possible and what
	 * objects are present.
	 * 
	 * @param direction
	 *            The direction the avatar wants to move.
	 * @return Returns true for valid movement, false for invalid movement.
	 */
	private boolean checkBoundaries(int direction) {
		int avatarNewX = 0;
		int avatarNewY = 0;

		switch (direction) {
		case UP:
			avatarNewY = avatar.getPositionY() - 1;
			if (avatarNewY >= 0) {
				/* Within playing field, inspect the new tile with inspectObject */
				return inspectObject(avatar.getPositionX(), avatarNewY,
						direction);
			} else {
				/* Edge of the screen, get items from next sublevel */
				leverloader = model.getLoader();
				model.setLevel(model.getLevel() - 1);
				leverloader.loadLevel("newLocation");
				return false;
			}

		case DOWN:
			avatarNewY = avatar.getPositionY() + 1;
			if (avatarNewY < getHeight()) {
				/* Within playing field, inspect the new tile with inspectObject */
				return inspectObject(avatar.getPositionX(), avatarNewY,
						direction);
			} else {
				/* Edge of the screen, get items from next sublevel */
				leverloader = model.getLoader();
				model.setLevel(model.getLevel() + 1);
				leverloader.loadLevel("newLocation");
				return false;
			}

		case LEFT:
			avatarNewX = avatar.getPositionX() - 1;
			if (avatarNewX >= 0) {
				/* Within playing field, inspect the new tile with inspectObject */
				return inspectObject(avatarNewX, avatar.getPositionY(),
						direction);
			} else {
				/*
				 * Edge of the screen, level shift are only made on the top and
				 * bottom of the map
				 */
				return false;
			}

		case RIGHT:
			avatarNewX = avatar.getPositionX() + 1;
			if (avatarNewX < getWidth()) {
				/* Within playing field, inspect the new tile with inspectObject */
				return inspectObject(avatarNewX, avatar.getPositionY(),
						direction);
			} else {
				/*
				 * Edge of the screen, level shift are only made on the top and
				 * bottom of the map
				 */
				return false;
			}
		default:
			return false;
		}
	}

	/**
	 * Checks what object is present at the location the avatar wants to move.
	 * 
	 * @param avatarNewX
	 *            Desired x of the avatar
	 * @param avatarNewY
	 *            Desired y of the avatar
	 * @return True for movement, false for no movement.
	 */
	private boolean inspectObject(int avatarNewX, int avatarNewY, int direction) {
		leverloader = model.getLoader();

		/** No object present, avatar can move. */
		if (getObject(avatarNewX, avatarNewY) == null) {
			return true;

		} else {
			activity.clearJoystick();

			/** Boss present, avatar will enter a fight and won't move. */
			if (getObject(avatarNewX, avatarNewY) instanceof Boss) {

				model.saveGame();
				Log.w("GameBoard.inspectObject", "ENTERING A FIGHT!");
				Boss boss = (Boss) (getObject(avatarNewX, avatarNewY));
				Intent combatIntent = new Intent(activity, CombatActivity.class);
				Bundle extras = new Bundle();
				extras.putString("bossName", boss.getName());
				combatIntent.putExtras(extras);
				activity.startActivityForResult(combatIntent, REQUEST_COMBAT_INTENT);
				return false;

				/** Door present */
			} else if (getObject(avatarNewX, avatarNewY) instanceof Door) {
				if (((Door) getObject(avatarNewX, avatarNewY)).isLocked()) {
					if (avatar.compareKey(((Door) getObject(avatarNewX, avatarNewY)).getType())) {
						/* If the door is locked and the avatar has a key */
						Toast.makeText(activity, "Door unlocked!", Toast.LENGTH_SHORT).show();
						removeObject(getObject(avatarNewX, avatarNewY));
					} else {
						/* door locked, no key */
						Toast.makeText(activity, "Door is locked", Toast.LENGTH_SHORT).show();
					}
				} else {
					/* Normal unlocked door */
					removeObject(getObject(avatarNewX, avatarNewY));
					updateView();
				}
				return false;

				/** Item present */
			} else if(getObject(avatarNewX,avatarNewY) instanceof ItemWrap){
				model.setSavedLocation(model.getAvatar().getPositionX(), model.getAvatar().getPositionY());
				Item item = ((ItemWrap)getObject(avatarNewX,avatarNewY)).getItem();
				model.addItemToList(item);
				removeObject(getObject(avatarNewX,avatarNewY));
				item.setLooted(true);
				model.getLoader().loadLevel("savedLocation");
				return false;		
			}
			
			/** Vending machine present */
			else if(getObject(avatarNewX,avatarNewY) instanceof VendingMachine){
				
				final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				final VendingMachine machine = ((VendingMachine)getObject(avatarNewX,avatarNewY));
				builder.setTitle("VendingMachine");
				builder.setCancelable(false);
				builder.setItems(new CharSequence[]{"Sell","Buy"}, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(which == 0) {
							AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
							builder2.setTitle("Sell    Coins");
							builder2.setPositiveButton("Back", new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   builder.show();
						           }
						       });
							builder2.setItems(model.returnItemNames(), new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int chosenItem) {
									Item item = model.getItemList().get(chosenItem);
									model.setBalance(model.getBalance() + item.getSellCosts());
									machine.addShopItem(item);
									model.removeItem(item);
									builder.show();
								}
							});
							builder2.show();
							
						}
						else {
							AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
							builder2.setTitle("Buy  			   Coins");
							builder2.setPositiveButton("Back", new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   builder.show();
						           }    
						       });
							builder2.setItems(machine.getShopList(), new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int chosenItem) {
								
									if(model.getBalance() > machine.getItem(chosenItem).getBuyCosts()){
										model.addItemToList(machine.getItem(chosenItem));
										model.setBalance(model.getBalance() - machine.getItem(chosenItem).getBuyCosts());
										machine.deleteItem(chosenItem);
										Toast.makeText(activity, "Item added!", Toast.LENGTH_SHORT).show();
										builder.show();
									}
									else {
										Toast.makeText(activity, "Insufficient money", Toast.LENGTH_SHORT).show();
										builder.show();
									}
								}
							});
							builder2.show();
						}	
					}
				});
				  builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   
			           }
			       });
				builder.show();
				return false;
				
			} else if (getObject(avatarNewX, avatarNewY) instanceof Elevator) {
				activity.disableMovement();
				/* Selects the floor */ 
				CharSequence levels[] = new CharSequence[] { "Ground floor", "First floor", "Second floor",
						"Third floor", "Fourth floor", "Cancel"};

				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Select a floor");
				builder.setCancelable(false);
				builder.setItems(levels, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int chosenFloor) {
						if (model.isLevelOpen(chosenFloor)) {
							String message = "";
							switch (chosenFloor) {
							case 0:
								model.setLevel(LevelLoader.GROUND_LEVEL_3);
								message = "Ground floor";
								break;
							case 1:
								model.setLevel(LevelLoader.FIRST_FLOOR_3);
								message = "First floor";
								break;
							case 2:
								model.setLevel(LevelLoader.SECOND_FLOOR_3);
								message = "Second floor";
								break;
							case 3:
								model.setLevel(LevelLoader.THIRD_FLOOR_3);
								message = "Third floor";
								break;
							case 4:
								model.setLevel(LevelLoader.FOURTH_FLOOR_4);
								message = "Fourth floor, home of Syntaxis";
								break;
							case 5:
								message = "Elevator leaves without you! Let's hope it will ever return";
								break;
							} 
							leverloader.loadLevel("Elevator");
							Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(activity, "Can't go there yet!", Toast.LENGTH_SHORT).show();
						}
						activity.enableMovement();
					}
				});
				builder.show();
				return false;

				/**
				 * Key is present, avatar gets it and can now open a locked door
				 */
			} else if (getObject(avatarNewX, avatarNewY) instanceof Key) {
				model.setSavedLocation(avatar.getPositionX(), avatar.getPositionY());
				avatar.addKey((Key) (getObject(avatarNewX, avatarNewY)));
				Toast.makeText(activity, "Found a key!", Toast.LENGTH_SHORT).show();
				model.getLoader().loadLevel("savedLocation");
				return false;

				/** ADD MORE CLASSES HERE */

				/*
				 * No tile present to move on, avatar won't move. [Out of
				 * bounds]
				 */
			} else {
				return false;
			}
		}
	}

	/** Save the core activity so the gameboard can start other activities. */
	public void setCoreActivity(CoreActivity activity) {
		this.activity = activity;
		model = ((StudyOrDieApplication) activity.getApplication()).getModel();
	}

	public CoreActivity getActivity() {
		return activity;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}
