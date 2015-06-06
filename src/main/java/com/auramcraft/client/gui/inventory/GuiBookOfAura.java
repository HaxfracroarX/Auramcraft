package com.auramcraft.client.gui.inventory;

import com.auramcraft.inventory.ContainerEmpty;
import com.auramcraft.reference.PageData;
import com.auramcraft.reference.PageIds;
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
	private boolean[] pages;
	private ArrayList<BookPage> book;
	private int currentPage = 0;
	
	public GuiBookOfAura() {
		super(new ContainerEmpty());
		xSize = 146;
		ySize = 180;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		buttonList.add(new ButtonTurnPage(0, guiLeft+105, guiTop+158, false));
		buttonList.add(new ButtonTurnPage(1, guiLeft+15, guiTop+158, true));
		buttonList.add(new ButtonBookTab(3, guiLeft+7, guiTop-33));
		buttonList.add(new ButtonBookTab(4, guiLeft+26, guiTop-33));
		buttonList.add(new ButtonBookTab(5, guiLeft+45, guiTop-33));
		
		AuramcraftPlayerStats stats = AuramcraftPlayerStats.get(mc.thePlayer);
		pages = stats.getPages();
		book = new ArrayList<BookPage>();
		makeBook(book, pages);
		page = book.get(currentPage);
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
		if(researchedPages(pages) == 1)
			researched = researchedPages(pages) + " Entry";
		else
			researched = researchedPages(pages) + " Entries";
		
		String pageNum = "p. "+(currentPage+1);
		
		GL11.glScalef(0.75f, 0.75f, 1f);
		
		fontRendererObj.drawString(pageNum, 20, 15, 4210752);
		fontRendererObj.drawString(researched, xSize-20, 15, 4210752);
		
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
		for(boolean page1 : pages) {
			researched += page1 ? 1 : 0;
		}
		return researched;
	}
	
	private BookPage getPageFromSlot(int slot) {
		switch(slot) {
			case PageIds.SHARDS:
				return new BookPage(PageData.SHARDS, PageIds.SHARDS);
			case PageIds.AURACRYSTAL:
				return new BookPage(PageData.AURACRYSTAL, PageIds.AURACRYSTAL);
			case PageIds.INFUSION:
				return new BookPage(PageData.INFUSION, PageIds.INFUSION);
			case PageIds.ALCHEMY:
				return new BookPage(PageData.ALCHEMY, PageIds.ALCHEMY);
			case PageIds.MAGIKA:
				return new BookPage(PageData.MAGIKA, PageIds.MAGIKA);
			case PageIds.INFUSION_TIER_1:
				return new BookPage(PageData.INFUSION_TIER_1, PageIds.INFUSION_TIER_1);
			case PageIds.WAND_CAP_IRON:
				return new BookPage(PageData.WAND_CAP_IRON, PageIds.WAND_CAP_IRON);
		}
		return new BookPage(PageData.EMPTY, PageIds.EMPTY);
	}
	
	private void makeBook(ArrayList<BookPage> book, boolean[] pages) {
		for(int i = 0; i < pages.length; i++) {
			if(pages[i])
				book.add(getPageFromSlot(i));
		}
		if(book.isEmpty())
			book.add(new BookPage(PageData.EMPTY, PageIds.EMPTY));
	}
	
	private void flipLeft() {
		if(currentPage >= 1) {
			currentPage--;
			page = book.get(currentPage);
		}
	}
	
	private void flipRight() {
		if(currentPage < book.size()-1) {
			currentPage++;
			page = book.get(currentPage);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch(button.id) {
			case 1:
				flipLeft();
				break;
			case 0:
				flipRight();
				break;
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
