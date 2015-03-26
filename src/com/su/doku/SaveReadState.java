package com.su.doku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

public class SaveReadState {

	int puzzleScale = 9;
	int queueSize = 5;
	int[][] puzzle = new int[puzzleScale][puzzleScale];
	int[][] showMatrix = new int[puzzleScale][puzzleScale];
	int[] queue = new int[queueSize];
	int[] dragCorrect;
	int time = 0;
	int saveLevel;
	int saveScore;
	String filename = "CurrentState.txt";
	File fileDir = new File(Environment.getExternalStorageDirectory().getPath()
			+ "/SuDoKu");

	public void SaveState(int[][] puzzle, int[][] currentMatrix, int[] queue,
			int[] dragCorrect, int level, int time, int score) {
		this.saveLevel = level;
		this.puzzle = puzzle;
		this.showMatrix = currentMatrix;
		this.queue = queue;
		this.dragCorrect = dragCorrect;
		this.time = time;
		this.saveScore = score;
		WriteFile();
	}

	private void WriteFile() {
		if (!FindDirectoryPath())
			return;
		BufferedWriter writer = null;
		File file = null;

		try {
			file = new File(fileDir.getAbsolutePath() + "/" + filename);
			file.delete();
			file.createNewFile();
			// ���I�s�X�榡�A�H�KŪ���ɤ���r�Ų��`
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true), "UTF-8"));

			// level
			writer.append(String.valueOf(saveLevel));
			writer.newLine();
			writer.flush();

			// �x�s���ׯx�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					writer.append(puzzle[i][j] + " ");
				}
				writer.newLine();
			}
			writer.flush(); // �����g�J�w�s�������e

			// �x�s�ثe��g���x�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					writer.append(showMatrix[i][j] + " ");
				}
				writer.newLine();
			}
			writer.flush();

			// �x�s�ثequeue�̭����Ʀr
			for (int i = 0; i < queueSize; i++) {
				writer.append(queue[i] + " ");
			}
			writer.newLine();
			writer.flush();

			// dragCorrect
			for (int i = 0; i < dragCorrect.length; i++) {
				writer.append(dragCorrect[i] + " ");
			}
			writer.newLine();
			writer.flush();

			// time
			writer.append(String.valueOf(time));
			writer.newLine();
			writer.flush();

			// score
			writer.append(String.valueOf(saveScore));
			writer.newLine();
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void ReadState() {
		File file = null;
		Scanner reader = null;
		try {
			file = new File(fileDir.getAbsolutePath() + "/" + filename);
			reader = new Scanner(file);
			
			//level
			saveLevel = reader.nextInt();
			// Ū�����ׯx�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					puzzle[i][j] = reader.nextInt();
				}
			}
			// Ū���ثe��g���x�}
			for (int i = 0; i < puzzleScale; i++) {
				for (int j = 0; j < puzzleScale; j++) {
					showMatrix[i][j] = reader.nextInt();
				}
			}
			// Ū���ثequeue�̭����Ʀr
			for (int i = 0; i < queueSize; i++) {
				queue[i] = reader.nextInt();
			}
			// dragCorrect
			dragCorrect = new int[(saveLevel + 1) * 9];
			for (int i = 0; i < (saveLevel + 1) * 9; i++) {
				dragCorrect[i] = reader.nextInt();
			}
			// �x�s�ثe�ɶ�
			time = reader.nextInt();
			//score
			saveScore = reader.nextInt();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}

	private boolean FindDirectoryPath() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_REMOVED)) {

			return false;
		} else {
			if (!fileDir.exists())
				fileDir.mkdirs();
			return true;
		}
	}

	public int[][] getAnswer() {
		return puzzle;
	}

	public int[][] getShowMatrix() {
		return showMatrix;
	}

	public int getTime() {
		return time;
	}
	
	public int getLevel() {
		return saveLevel;
	}
	public int getScore() {
		return saveScore;
	}
	
	public int[] getQueue() {
		return queue;
	}
	
	public int[] getDragCorrect() {
		return dragCorrect;
	}
}
