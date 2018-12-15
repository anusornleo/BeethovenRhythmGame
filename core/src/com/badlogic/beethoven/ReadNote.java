package com.badlogic.beethoven;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ReadNote {

	public static GetNote[] notes;
	static String[] path = {"song\\txt\\song0.txt","song\\txt\\song1.txt","song\\txt\\song2.txt","song\\txt\\song3.txt"};

	public static void readFile(int num_b) {
		FileHandle file = Gdx.files.internal(path[num_b]);
		String text = file.readString();
		
		String allNote[] = text.split("\\r?\\n");
		notes = new GetNote[allNote.length];
		int x=0;
		for (String note : allNote) {
			String value[] = note.split("\\s+");
			for (int i = 0; i < 3; i++) {
				notes[x] = new GetNote(Integer.parseInt(value[0]), Double.parseDouble(value[1]), Integer.parseInt(value[2]));
			}
			x++;
		}
		Play.addNewNote(notes);
		
		
	}
}
