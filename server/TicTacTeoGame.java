package twicetry.server;

import twicetry.IndexForGame;
import twicetry.OtherConnectionTo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class TicTacTeoGame implements Runnable, IndexForGame
{
	private int row, col;
	private boolean waiting = true;
	private boolean chessBack = false;
	private boolean exit = false;
	private Socket user1;
	private Socket user2;
	private CopyOnWriteArrayList<Socket> all = new CopyOnWriteArrayList<Socket>();
	private int[][] cell = new int[15][15];
	private  DataOutputStream toUser1;
	private DataOutputStream toUser2;
	public TicTacTeoGame(Socket user1, Socket user2)
	{
		this.user1 = user1;
		this.user2 = user2;
		all.add(user1);
		all.add(user2);
		try
		{
			toUser1 = new DataOutputStream(this.user1.getOutputStream());
			toUser2 = new DataOutputStream(this.user2.getOutputStream());
		}
		catch (IOException e){
			System.out.println("-----2-----");
			release();
		}

		new Thread(new DataGet(user1, user2)).start();
		new Thread(new DataGet(user2,user1)).start();
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[0].length; j++) {
				cell[i][j] = 0;
			}
		}
	}

	@Override
	public void run()
	{
		try
		{
			toUser1.writeInt(0);
		} catch (Exception e)
		{
			release();
		}
		while (!exit)
		{
			try
			{
				waiting();
				cell[row][col] = 1;
				if (isWon() == 1)
				{
					toUser1.writeInt(1);
					toUser1.writeInt(p1win);
					toUser2.writeInt(1);
					toUser2.writeInt(p1win);
					getevent(toUser2, row, col);
					exit = true;
					break;
				} else if (isFull())
				{
					toUser1.writeInt(1);
					toUser1.writeInt(equalpp);
					toUser2.writeInt(1);
					toUser2.writeInt(equalpp);
					getevent(toUser2, row, col);
					exit = true;
					break;
				} else
				{
					toUser2.writeInt(1);
					toUser2.writeInt(going);
					getevent(toUser2, row, col);
				}
				waiting();
				cell[row][col] = 2;

				if (isWon() == 2)
				{
					toUser1.writeInt(1);
					toUser1.writeInt(p2win);
					toUser2.writeInt(1);
					toUser2.writeInt(p2win);
					getevent(toUser1, row, col);
					exit = true;
					break;
				} else
				{
					toUser1.writeInt(1);
					toUser1.writeInt(going);
					getevent(toUser1, row, col);
				}
			}
			catch (Exception e)
			{
				release();
			}
		}
	}
	private void waiting() throws InterruptedException{
		while(waiting){
			if(chessBack)
			{
				cell[row][col] = 0;
				chessBack = false;
			}
			Thread.sleep(100);
		}
		waiting = true;
	}

	private int isWon() {
		int user;
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[0].length && cell[0].length - j >= 3; j++) {
				user = cell[i][j];
				int t = 1;
				for (int k = j; k < cell[0].length - 1; k++) {
					if (user != 0 && user == cell[i][k + 1]) {
						t++;
						if (t == 3) {
							return user;
						}} else {break;}}}}
		for (int j = 0; j < cell[0].length; j++) {
			for (int i = 0; i < cell.length && cell.length - i >= 3; i++) {
				user = cell[i][j];
				int t = 1;
				for (int k = i; k < cell.length - 1; k++) {
					if (user != 0 && user == cell[k + 1][j]) {
						t++;
						if (t == 3) {
							return user;
						}} else {break;}}}}
		for (int i = 0; i < cell.length && cell.length - i >= 2; i++) {
			for (int j = 0; j < cell[0].length && cell[0].length - j >= 3; j++) {
				user = cell[i][j];
				int t = 1;
				for (int x = i, y = j; x < cell.length - 1 && y < cell[0].length - 1; x++, y++) {
					if (user != 0 && user == cell[x + 1][y + 1]) {
						t++;
						if (t == 3) {
							return user;
						}
					} else {break;}}}}
		for (int i = 14; i >= 2; i--) {
			for (int j = 0; j < cell[0].length && cell[0].length - j >= 3; j++) {
				user = cell[i][j];
				int t = 1;
				for (int x = i, y = j; x >= 1 && y < cell[0].length - 1; x--, y++) {
					if (user != 0 && user == cell[x - 1][y + 1]) {
						t++;
						if (t == 3) {
							return user;
						}
					} else {break;}}}}
		return 3;}

	private void getevent(DataOutputStream out, int row, int col) throws IOException
	{
		out.writeInt(row);
		out.writeInt(col);
	}

	//判断棋盘是否已满
	private boolean isFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}
	private void release()
	{
		exit = true;
		OtherConnectionTo.close(user1, user2, toUser1, toUser2);
	}
	class DataGet implements Runnable
	{
		private int x;
		private String str1 = "";

		private Socket user1;
		private Socket user2;

		private boolean exit;

		private DataInputStream Input;
		private DataOutputStream Output;

		public DataGet(Socket user1, Socket user2)
		{
			this.user1 = user1;
			this.user2 = user2;
			try
			{
				Input = new DataInputStream(this.user1.getInputStream());
				Output = new DataOutputStream(this.user2.getOutputStream());
			} catch (IOException e)
			{
				release();
			}
		}
		@Override
		public void run()
		{
			while (!exit)
			{
				try
				{x = Input.readInt();
					if (x == 2)
					{
						str1 = Input.readUTF();
						Output.writeInt(2);
						Output.writeUTF(str1);
					} else if (x == 1)
					{
						row = Input.readInt();
						col = Input.readInt();
						waiting = false;
					}
					else if (x == 3)
					{
						chessBack = true;
						Output.writeInt(3);
					}
					else if (x == 4)
					{
						Boolean bl = Input.readBoolean();
						Output.writeInt(4);
						Output.writeBoolean(bl);
					}
				} catch (Exception e)
				{
					all.remove(user1);
					waiting = false;

					try
					{
						for (Socket socket : all)
						{
							if (socket == user2)
								Output.writeInt(20);
						}

					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
					release();
				}
			}
		}


		private void release()
		{
			exit = true;
			OtherConnectionTo.close(user1, user2, Input, Output);
		}

		public int getRow()
		{
			return row;
		}

		public int getCol()
		{
			return col;
		}

		public boolean isChessBack()
		{
			return chessBack;
		}

	}
}



