package com.auramcraft.client.gui.inventory;

import com.auramcraft.inventory.ContainerEmpty;
import com.auramcraft.reference.BookIds;
import com.auramcraft.reference.Textures;
import com.auramcraft.stats.AuramcraftPlayerStats;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class GuiBookOfAura extends GuiContainer {
	private BookPage page;
	private ArrayList<boolean[]> pages;
	private ArrayList<ArrayList<BookPage>> book;
	private int currentTab = 0;
	private int currentPage = 0;
	
	public GuiBookOfAura() {
		super(new ContainerEmpty());
		xSize = 146;
		ySize = 180;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		buttonList.add(new ButtonTurnPage(0, guiLeft+15, guiTop+158, false));
		buttonList.add(new ButtonTurnPage(1, guiLeft+105, guiTop+158, true));
		
		for(int i = 0; i < BookIds.tabs; i++)
			buttonList.add(new ButtonBookTab(i+3, guiLeft+(i*19)+7, guiTop-15));
		
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(mc.thePlayer);
		
		pages = new ArrayList<boolean[]>();
		for(int i = 0; i < BookIds.tabs; i++)
			pages.add(stats.getPages(i));
		
		book = new ArrayList<ArrayList<BookPage>>();
		makeBook(book, pages);
		page = book.get(currentTab).get(0);
	}
	
	/**
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	public void drawGuiContainerForegroundLayer(int mx, int my) {
		/*	Draw Header	 */
		String researched;
		
		// We want to have good grammars
		int entries = researchedPages(pages.get(currentTab));
		if(entries == 1)
			researched = entries + " Entry";
		else
			researched = entries + " Entries";
		
		String pageNum = "p. "+(currentPage+1);
		
		GL11.glScalef(0.75f, 0.75f, 1f);
		
		fontRendererObj.drawString(pageNum, 20, 15, 0x404040);
		fontRendererObj.drawString(researched, xSize-20, 15, 0x404040);
		
		/*	Draw Footer	*/
		String tabName = BookIds.getTab(currentTab).getName();
		
		fontRendererObj.drawString(tabName, (int) ((xSize - fontRendererObj.getStringWidth(tabName))/2*1.35), (int) (ySize*1.33 - 17*1.33), 0x404040);
		
		/*	Draw Page Data	*/
		String file;
		InputStream fileStream;
		
		// Get an InputStream for the file, then convert to String
		try {
			 fileStream = mc.getResourceManager().getResource(page.getData()).getInputStream();
		}
		catch(IOException e) {
			e.printStackTrace();
			fileStream = null;
		}
		file = convertStreamToString(fileStream);
		
		// Get the Head and Body of the page
		int startOfHead = file.indexOf("\r\n")+2;
		int endOfHead = file.indexOf("\r\nBody:\r\n");
		String head = file.substring(startOfHead, endOfHead);
		
		int startOfBody = file.indexOf("\r\n", endOfHead+4)+2;
		int endOfBody = file.indexOf("\r\n", startOfBody);
		String body = file.substring(startOfBody, endOfBody);
		
		// Draw Head
		GL11.glScalef(4f/3f, 4f/3f, 1f);
		fontRendererObj.drawString(head, (xSize - fontRendererObj.getStringWidth(head))/2, 25, 0x404040);
		
		// Draw Body
		GL11.glScalef(0.75f, 0.75f, 1f);
		
		// Draws Body line by line
		int LINE_LENGTH = 29;
		int position = 0;
		int endpos = LINE_LENGTH;
		int i;
		for(i = 0; body.length() - position >= LINE_LENGTH; i++, position = endpos, endpos += LINE_LENGTH) {
			// We don't want to start a new line with whitespace
			while(body.substring(position, position+1).equals(" "))
				position++;
			
			// But we do want to end with it
			while(!body.substring(endpos-1, endpos).equals(" "))
				endpos--;
			
			// Draw the new line
			fontRendererObj.drawString(body.substring(position, endpos), 25, 55 + (i)*(fontRendererObj.FONT_HEIGHT + 2), 0x404040);
		}
		
		// Draw last line of body
		fontRendererObj.drawString(body.substring(position), 25, 55 + i*(fontRendererObj.FONT_HEIGHT + 2), 0x404040);
	}

	/**
	 * @param renderPartialTicks Partial ticks from last render
	 * @param mx Mouse x
	 * @param my Mouse y
	 */
	@Override
	public void drawGuiContainerBackgroundLayer(float renderPartialTicks, int mx, int my) {
		GL11.glColor4f(1f, 1f, 1f, 1f);
		
		// Draw Background
		mc.getTextureManager().bindTexture(Textures.GUI.GUI_BOOK_OF_AURA);
	    drawTexturedModalRect(guiLeft, guiTop, 20, 1, xSize, ySize);
	}
	
	private int researchedPages(boolean[] pages) {
		int researched = 0;
		for(boolean page : pages)
			researched += page ? 1 : 0;
		
		return researched;
	}
	
	private void makeBook(ArrayList<ArrayList<BookPage>> book, ArrayList<boolean[]> pages) {
		for(int i = 0; i < pages.size(); i++) {
			book.add(new ArrayList<BookPage>());
			
			for(int j = 0; j < pages.get(i).length; j++) {
				if(pages.get(i)[j])
					book.get(i).add(BookIds.getTab(i).getPages().get(j));
			}
			
			if(book.get(i).isEmpty())
				book.get(i).add(BookIds.pageEmpty);
		}
	}
	
	private void flipLeft() {
		if(currentPage >= 1) {
			currentPage--;
			page = book.get(currentTab).get(currentPage);
		}
	}
	
	private void flipRight() {
		if(currentPage < book.get(currentTab).size()-1) {
			currentPage++;
			page = book.get(currentTab).get(currentPage);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		// Left Arrow
		if(button.id == 0)
			flipLeft();
		// Right Arrow
		else if(button.id == 1)
			flipRight();
		// Switch Tab
		else {
			currentTab = button.id-3;
			currentPage = 0;
			page = book.get(currentTab).get(currentPage);
		}
	}

	/**
	 * Fancy little method I found
	 */
	static String convertStreamToString(InputStream is) {
    	Scanner s = new Scanner(is).useDelimiter("\\A");
    	return s.hasNext() ? s.next() : "";
	}
}
