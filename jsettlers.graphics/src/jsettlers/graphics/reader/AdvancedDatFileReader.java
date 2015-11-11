/*******************************************************************************
 * Copyright (c) 2015
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package jsettlers.graphics.reader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import jsettlers.graphics.image.AnimationSequence;
import jsettlers.graphics.image.GuiImage;
import jsettlers.graphics.image.Image;
import jsettlers.graphics.image.LandscapeImage;
import jsettlers.graphics.image.MultiImageMap;
import jsettlers.graphics.image.NullImage;
import jsettlers.graphics.image.SettlerImage;
import jsettlers.graphics.image.ShadowImage;
import jsettlers.graphics.image.SingleImage;
import jsettlers.graphics.image.Torso;
import jsettlers.graphics.reader.bytereader.ByteReader;
import jsettlers.graphics.reader.translator.DatBitmapTranslator;
import jsettlers.graphics.reader.translator.GuiTranslator;
import jsettlers.graphics.reader.translator.LandscapeTranslator;
import jsettlers.graphics.reader.translator.SettlerTranslator;
import jsettlers.graphics.reader.translator.ShadowTranslator;
import jsettlers.graphics.reader.translator.TorsoTranslator;
import jsettlers.graphics.sequence.ArraySequence;
import jsettlers.graphics.sequence.Sequence;

/**
 * This is an advanced dat file reader. It can read the file, but it only reads needed sequences.
 * <p>
 * The format of a dat file is (all numbers in little endian):
 * <table>
 * <tr>
 * <td>Bytes 0..47:</td>
 * <td>Always the same</td>
 * </tr>
 * <tr>
 * <td>Bytes 48 .. 51:</td>
 * <td>file size</td>
 * </tr>
 * <tr>
 * <td>Bytes 52 .. 55:</td>
 * <td>Start position for Texts/Strings.</td>
 * </tr>
 * <tr>
 * <td>Bytes 56 .. 59:</td>
 * <td>Start position of landscape sequence pointers.</td>
 * </tr>
 * <tr>
 * <td>Bytes 60 .. 63:</td>
 * <td>Start position for GUI graphics objects</td>
 * </tr>
 * <tr>
 * <td>Bytes 64 .. 67:</td>
 * <td>Settler/Building/.. pointers</td>
 * </tr>
 * <tr>
 * <td>Bytes 68 .. 71:</td>
 * <td>Torso pointers</td>
 * </tr>
 * <tr>
 * <td>Bytes 72 .. 75:</td>
 * <td>Shadow pointers</td>
 * </tr>
 * <tr>
 * <td>Bytes 76 .. 79:</td>
 * <td>Animation Sequences pointers</td>
 * </tr>
 * <tr>
 * <td>Bytes 80 .. 83:</td>
 * <td>Color Palettes pointers (for Torsos)</td>
 * </tr>
 * <tr>
 * <td>Bytes 84 .. 87:</td>
 * <td>{04 19 00 00}</td>
 * </tr>
 * <tr>
 * <td>Bytes 88 .. 91:</td>
 * <td>{0c 00 00 00}</td>
 * </tr>
 * <tr>
 * <td>Bytes 92 .. 95:</td>
 * <td>{00 00 00 00}</td>
 * </tr>
 * <tr>
 * <td>e.g. Bytes 102 .. 103:</td>
 * <td>Image count of image sequences for one type</td>
 * </tr>
 * <tr>
 * <td>e.g. Bytes 104 .. 107:</td>
 * <td>Start position of fist image sequence list.</td>
 * </tr>
 * </table>
 * 
 * GFX Files:
 * Siedler3_00.f8007e01f.dat	Landscape
 * Siedler3_01.f8007e01f.dat	objects / stacks / color palettes
 * Siedler3_02.f8007e01f.dat	menu gui
 * Siedler3_03.f8007e01f.dat	in game gui buttons
 * Siedler3_04.f8007e01f.dat	in game gui
 * Siedler3_05.f8007e01f.dat	(empty)
 * Siedler3_06.f8007e01f.dat	resources
 * Siedler3_07.f8007e01f.dat	Text / Strings (in Game)
 * Siedler3_08.f8007e01f.dat	Text / Strings (Mission)
 * Siedler3_09.f8007e01f.dat	Text / Strings (out of: e.g. main menu)
 *
 * Siedler3_10.f8007e01f.dat	Roman bearers
 * Siedler3_11.f8007e01f.dat	Roman workers
 * Siedler3_12.f8007e01f.dat	Roman warriors
 * Siedler3_13.f8007e01f.dat	Roman buildings
 * Siedler3_14.f8007e01f.dat	Roman gdi
 * Siedler3_15.f8007e01f.dat	Roman animation sequences
 * Siedler3_16.f8007e01f.dat	(empty)
 * Siedler3_17.f8007e01f.dat	(empty)
 * Siedler3_18.f8007e01f.dat	(windows dll?)
 *
 * Siedler3_20.f8007e01f.dat	Egyptian bearerr
 * Siedler3_21.f8007e01f.dat	Egyptian worker
 * Siedler3_22.f8007e01f.dat	Egyptian warriors
 * Siedler3_23.f8007e01f.dat	Egyptian buildings
 * Siedler3_24.f8007e01f.dat	Egyptian gui
 * Siedler3_25.f8007e01f.dat	Egyptian animation sequences
 * Siedler3_26.f8007e01f.dat	(empty)
 * Siedler3_27.f8007e01f.dat	(empty)
 *
 * Siedler3_30.f8007e01f.dat	Asians bearerr
 * Siedler3_31.f8007e01f.dat	Asians worker
 * Siedler3_32.f8007e01f.dat	Asians warriors
 * Siedler3_33.f8007e01f.dat	Asians buildings
 * Siedler3_34.f8007e01f.dat	Asians gui
 * Siedler3_35.f8007e01f.dat	Asians animation sequences
 * Siedler3_36.f8007e01f.dat	Asians ships
 * Siedler3_37.f8007e01f.dat	(empty)
 * 
 * Siedler3_40.f8007e01f.dat	Amazon bearerr
 * Siedler3_41.f8007e01f.dat	Amazon worker
 * Siedler3_42.f8007e01f.dat	Amazon warriors
 * Siedler3_43.f8007e01f.dat	Amazon buildings
 * Siedler3_44.f8007e01f.dat	Amazon gui
 * Siedler3_45.f8007e01f.dat	Amazon animation sequences
 * Siedler3_46.f8007e01f.dat	Amazon ships
 * Siedler3_47.f8007e01f.dat	(empty)
 * 
 * Siedler3_48.f8007e01f.dat	mission map-images (Amazon)
 * Siedler3_60.f8007e01f.dat	mission map-images
 * 
 * Siedler3_61.f8007e01f.dat	Settler Logos
 * 
 *
 * @author michael
 * @author Thomas Zeugner
 */
public class AdvancedDatFileReader implements DatFileSet {
	/**
	 * Every dat file seems to have to start with this sequence.
	 */
	private static final byte[] FILE_START1 = {
			0x04,
			0x13,
			0x04,
			0x00,
			0x0c,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x54,
			0x00,
			0x00,
			0x00,
			0x20,
			0x00,
			0x00,
			0x00,
			0x40,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x10,
			0x00,
			0x00,
			0x00,
			0x00,
	};
	private static final byte[] FILE_START2 = {
			0x00,
			0x00,
			0x1f,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00
	};

	private static final byte[] FILE_HEADER_END = {
			0x04,
			0x19,
			0x00,
			0x00,
			0x0c,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00,
			0x00
	};

	public enum EDataFileDataTypes {
		SETTLER (0x106),	
		TORSO(0x3112),
		LANDSCAPE(0x2412),	
		SHADOW(0x5982),
		// fullscreen images
		GUI (0x11306),
		//- Strings
		TEXT(0x1904),
		//- color Paletts for Torsos
		COLOR_PALETT(0x2607),
		//- Animations Sequences
		ANIMATION_SEQUENCE(0x21702);
		
		public final int FileID;
		public static final int length = EDataFileDataTypes.values().length;
		public final int index;
		
		EDataFileDataTypes(int fileID) {
			FileID = fileID;
			index = this.ordinal();
		}
		
		public static EDataFileDataTypes FromSequenceType(int FileID) {
			for (int i = 0; i < length; i++) {
				if (EDataFileDataTypes.values()[i].FileID == FileID) return EDataFileDataTypes.values()[i];
			}
			
			return null;
		}
	}

	
	private final DatBitmapTranslator<SettlerImage> settlerTranslator;

	private final DatBitmapTranslator<Torso> torsoTranslator;

	private final DatBitmapTranslator<LandscapeImage> landscapeTranslator;

	private final DatBitmapTranslator<ShadowImage> shadowTranslator;

	private final DatBitmapTranslator<GuiImage> guiTranslator;

	private ByteReader reader = null;
	private final File file;

	/**
	 * This is a list of file positions where Data/Images/Sequences/... start.
	 */
	private int[][] indexs = new int [EDataFileDataTypes.length][];

	/**
	 * A list of loaded settler sequences.
	 */
	private Sequence<Image>[] settlersequences = null;
	
	
	private AnimationSequence[] animationSequence = null;
	
	/**
	 * A list of loaded landscape images.
	 */
	private LandscapeImage[] landscapeimages = null;
	private final Sequence<LandscapeImage> landscapesequence = new LandscapeImageSequence();

	private GuiImage[] guiimages = null;
	private final Sequence<GuiImage> guisequence = new GuiImageSequence();

	private final SequenceList<Image> directSettlerList;

	
	private static final byte[] START = new byte[] {
			0x02, 0x14, 0x00, 0x00, 0x08, 0x00, 0x00
	};

	private final DatFileType type;

	public AdvancedDatFileReader(File file, DatFileType type) {
		this.file = file;
		this.type = type;
		
		directSettlerList = new DirectSettlerSequenceList();

		initializeNullFile();
		
		settlerTranslator = new SettlerTranslator(type);
		torsoTranslator = new TorsoTranslator();
		landscapeTranslator = new LandscapeTranslator(type);
		shadowTranslator = new ShadowTranslator();
		guiTranslator = new GuiTranslator(type);
	}

	/**
	 * Initializes the reader, reads the index.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		try {
			try {
				reader = new ByteReader(new RandomAccessFile(file, "r"));
				initFromReader(file, reader);

			} catch (IOException e) {
				if (reader != null) {
					reader.close();
					reader = null;
				}
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		initializeNullFile();

		//- helper variables for the indexes
		final int SettlerID = EDataFileDataTypes.SETTLER.index;
		final int TorsoID = EDataFileDataTypes.TORSO.index;
		final int ShadowID = EDataFileDataTypes.SHADOW.index;
		final int LansscapeID = EDataFileDataTypes.LANDSCAPE.index;
		final int GuiID = EDataFileDataTypes.GUI.index;
		final int SequenceID = EDataFileDataTypes.ANIMATION_SEQUENCE.index;
		
		//- init Size of Images-Objects
		guiimages = new GuiImage[indexs[GuiID].length];
		landscapeimages = new LandscapeImage[indexs[LansscapeID].length];
		settlersequences = new Sequence[indexs[SettlerID].length];
		animationSequence = new AnimationSequence[indexs[SequenceID].length];
		
		
		int torsodifference = indexs[SettlerID].length - indexs[TorsoID].length;
		if (torsodifference != 0) {
			int[] oldtorsos = indexs[TorsoID];
			indexs[TorsoID] = new int[indexs[SettlerID].length];
			for (int i = 0; i < oldtorsos.length; i++) {
				indexs[TorsoID][i + torsodifference] = oldtorsos[i];
			}
			for (int i = 0; i < torsodifference; i++) {
				indexs[TorsoID][i] = -1;
			}
		}

		int shadowdifference = indexs[SettlerID].length - indexs[ShadowID].length;
		if (shadowdifference > 0) {
			int[] oldshadows = indexs[ShadowID];
			indexs[ShadowID] = new int[indexs[SettlerID].length];
			for (int i = 0; i < oldshadows.length; i++) {
				indexs[ShadowID][i + shadowdifference] = oldshadows[i];
			}
			for (int i = 0; i < shadowdifference; i++) {
				indexs[ShadowID][i] = -1;
			}
		}
		
	}

	private void initFromReader(File file, ByteReader reader)
			throws IOException {
		int[] sequenceIndexStarts =
				readSequenceIndexStarts(file.length(), reader);

		//- read all resources
		for (int i = 0; i < EDataFileDataTypes.length; i++) {
			try {
				readSequencesAt(reader, sequenceIndexStarts[i]);
			} catch (IOException e) {
				System.err.println("Error while loading sequence" + ": "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private int[] readSequenceIndexStarts(long filelength,
			ByteReader reader) throws IOException {
		reader.assumeToRead(FILE_START1);
		reader.assumeToRead(type.getFileStartMagic());
		reader.assumeToRead(FILE_START2);
		int fileSize = reader.read32();

		if (fileSize != filelength) {
			throw new IOException(
					"The length stored in the dat file is not the file length.");
		}


		// read settler image pointer
		int[] sequenceIndexStarts = new int[EDataFileDataTypes.length];
		for (int i = 0; i <  EDataFileDataTypes.length; i++) {
			sequenceIndexStarts[i] = reader.read32();
		}

		// ignore unknown bytes.
		reader.assumeToRead(FILE_HEADER_END);
		return sequenceIndexStarts;
	}

	/**
	 * reads all sequence starts at a given position.
	 * <p>
	 * Does not align torsos and shadows.
	 * 
	 * @param reader
	 *            The reader to read from.
	 * @param sequenceIndexStart
	 *            The position to start at.
	 * @param type
	 *            The type of the sequence
	 * @throws IOException
	 *             if an read error occurred.
	 */
	private void readSequencesAt(ByteReader reader, int sequenceIndexStart)
			throws IOException {
		// read data index 0
		reader.skipTo(sequenceIndexStart);

		int sequenceType = reader.read32();

		//- size of sequenceIndexPointers in Bytes
		int byteCount = reader.read16();
		
		//- number of indexes in sequenceIndexPointers
		int pointerCount = reader.read16();

		int headerLength = 8;
		
		//- special cases for Texts & Paletts - they use a padding DWord
		if (sequenceType == EDataFileDataTypes.TEXT.FileID) {
			//- the count for Text is 0... don't know why. 
			pointerCount = (byteCount - 12) / 4;
			reader.read32(); //- unknown DWord
			headerLength += 4;
		}
		
		if (sequenceType == EDataFileDataTypes.COLOR_PALETT.FileID) {
			reader.read32(); //- unknown DWord
			headerLength += 4;
		}
		
		if (byteCount != pointerCount * 4 + headerLength) {
			throw new IOException("Sequence index block length ("
					+ pointerCount + ") and " + "bytecount (" + byteCount
					+ ") are not consistent.");
		}


		int[] sequenceIndexPointers = new int[pointerCount];
		for (int i = 0; i < pointerCount; i++) {
			sequenceIndexPointers[i] = reader.read32();
		}

		//- save Indexes to 
		EDataFileDataTypes TypeInfo = EDataFileDataTypes.FromSequenceType(sequenceType);
		indexs[TypeInfo.index] = sequenceIndexPointers;
		
	}

	private void initializeNullFile() {
		for (int i = 0; i < EDataFileDataTypes.length; i++) {
			if (indexs[i] == null) {
				indexs[i] = new int[0];
			}
		}
	}

	private void initializeIfNeeded() {
		if (settlersequences == null) {
			initialize();
		}
	}

	@Override
	public SequenceList<Image> getSettlers() {
		return directSettlerList;
	}

	private static final Sequence<Image> NULL_SETTLER_SEQUENCE =
			new ArraySequence<Image>(new SettlerImage[0]);

	private class DirectSettlerSequenceList implements SequenceList<Image> {

		@Override
		public Sequence<Image> get(int index) {
			initializeIfNeeded();
			if (settlersequences[index] == null) {
				settlersequences[index] = NULL_SETTLER_SEQUENCE;
				try {
					System.out.println("Loading Sequence number " + index);

					loadSettlers(index);
				} catch (Exception e) {
				}
			}
			return settlersequences[index];
		}

		@Override
		public int size() {
			initializeIfNeeded();
			return settlersequences.length;
		}
	}

	private synchronized void loadAnimationSequences(int index) throws IOException {
		
		int position = indexs[EDataFileDataTypes.ANIMATION_SEQUENCE.index][index];
		reader.skipTo(position);
		
		int count = reader.read32();
		
		animationSequence[index] = new AnimationSequence(count);
		
		for (int i = 0; i < count; i++) {
			animationSequence[index].set(i, reader);
		}
		
	}
	
	private synchronized void loadSettlers(int index) throws IOException {

		int position = indexs[EDataFileDataTypes.SETTLER.index][index];
		long[] framePositions = readSequenceHeader(position);

		SettlerImage[] images = new SettlerImage[framePositions.length];
		
		for (int i = 0; i < framePositions.length; i++) {
			reader.skipTo(framePositions[i]);
			images[i] = DatBitmapReader.getImage(settlerTranslator, reader);
		}
		
		int indexTorso = index;
		int torsoposition = indexs[EDataFileDataTypes.TORSO.index][indexTorso];
		if (torsoposition >= 0) {
			long[] torsoPositions = readSequenceHeader(torsoposition);
			
			for (int i = 0; i < torsoPositions.length 
					&& i < framePositions.length; i++) {
				
				reader.skipTo(torsoPositions[i]);
				Torso torso = DatBitmapReader.getImage(torsoTranslator, reader);
				images[i].setTorso(torso);
			}
		}

		settlersequences[index] = new ArraySequence<Image>(images);
	}

	private long[] readSequenceHeader(int position) throws IOException {
		reader.skipTo(position);

		reader.assumeToRead(START);
		int frameCount = reader.read8();

		long[] framePositions = new long[frameCount];
		for (int i = 0; i < frameCount; i++) {
			framePositions[i] = reader.read32() + position;
		}
		return framePositions;
	}

	@Override
	public Sequence<LandscapeImage> getLandscapes() {
		return landscapesequence;
	}

	@Override
	public Sequence<GuiImage> getGuis() {
		return guisequence;
	}

	/**
	 * This landscape image list loads the landscape images.
	 * 
	 * @author michael
	 */
	private class LandscapeImageSequence implements Sequence<LandscapeImage> {
		/**
		 * Forces a get of the image.
		 */
		@Override
		public LandscapeImage getImage(int index) {
			initializeIfNeeded();
			if (landscapeimages[index] == null) {
				loadLandscapeImage(index);
			}
			return landscapeimages[index];
		}

		@Override
		public int length() {
			initializeIfNeeded();
			return landscapeimages.length;
		}

		@Override
		public SingleImage getImageSafe(int index) {
			initializeIfNeeded();
			if (index < 0 || index >= length()) {
				return NullImage.getInstance();
			} else {
				if (landscapeimages[index] == null) {
					loadLandscapeImage(index);
				}
				return landscapeimages[index];
			}
		}
	}

	public ByteReader getReaderForLandscape(int index) throws IOException {
		initializeIfNeeded();
		reader.skipTo(indexs[EDataFileDataTypes.LANDSCAPE.index][index]);
		return reader;
	}

	private void loadLandscapeImage(int index) {
		try {
			reader.skipTo(indexs[EDataFileDataTypes.LANDSCAPE.index][index]);
			LandscapeImage image =
					DatBitmapReader.getImage(landscapeTranslator, reader);
			landscapeimages[index] = image;
		} catch (IOException e) {
			landscapeimages[index] = NullImage.getForLandscape();
		}
	}

	/**
	 * This landscape image list loads the landscape images.
	 * 
	 * @author michael
	 */
	private class GuiImageSequence implements Sequence<GuiImage> {
		/**
		 * Forces a get of the image.
		 */
		@Override
		public GuiImage getImage(int index) {
			initializeIfNeeded();
			if (guiimages[index] == null) {
				loadGuiImage(index);
			}
			return guiimages[index];
		}

		@Override
		public int length() {
			initializeIfNeeded();
			return guiimages.length;
		}

		@Override
		public SingleImage getImageSafe(int index) {
			initializeIfNeeded();
			if (index < 0 || index >= length()) {
				return NullImage.getInstance();
			} else {
				if (guiimages[index] == null) {
					loadGuiImage(index);
				}
				return guiimages[index];
			}
		}
	}

	private void loadGuiImage(int index) {
		try {
			reader.skipTo(indexs[EDataFileDataTypes.GUI.index][index]);
			GuiImage image = DatBitmapReader.getImage(guiTranslator, reader);
			guiimages[index] = image;
		} catch (IOException e) {
			guiimages[index] = NullImage.getForGui();
		}
	}

	public long[] getSettlerPointers(int seqindex) throws IOException {
		initializeIfNeeded();
		return readSequenceHeader(indexs[EDataFileDataTypes.SETTLER.index][seqindex]);
	}

	public long[] getTorsoPointers(int seqindex) throws IOException {
		initializeIfNeeded();
		int position = indexs[EDataFileDataTypes.TORSO.index][seqindex];
		if (position >= 0) {
			return readSequenceHeader(position);
		} else {
			return null;
		}
	}

	/**
	 * Gets a reader positioned at the given settler
	 * 
	 * @param pointer
	 * @return
	 * @throws IOException
	 */
	public ByteReader getReaderForPointer(long pointer) throws IOException {
		initializeIfNeeded();
		reader.skipTo(pointer);
		return reader;
	}

	public void generateImageMap(int width, int height, int[] sequences,
			String id) throws IOException {
		initializeIfNeeded();

		MultiImageMap map = new MultiImageMap(width, height, id);
		if (!map.hasCache()) {
			map.addSequences(this, sequences, settlersequences);
			map.writeCache();
		}
	}

	public DatBitmapTranslator<SettlerImage> getSettlerTranslator() {
		return settlerTranslator;
	}

	public DatBitmapTranslator<Torso> getTorsoTranslator() {
		return torsoTranslator;
	}

	public DatBitmapTranslator<LandscapeImage> getLandscapeTranslator() {
		return landscapeTranslator;
	}
}