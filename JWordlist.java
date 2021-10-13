import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class JWordlist 
{
	public static void main(String[] args)
	{

		System.out.println("       ___          __           _ _ _     _   ");
		System.out.println("      | \\ \\        / /          | | (_)   | |  ");
		System.out.println("      | |\\ \\  /\\  / /__  _ __ __| | |_ ___| |_ ");
		System.out.println("  _   | | \\ \\/  \\/ / _ \\| '__/ _` | | / __| __|");
		System.out.println(" | |__| |  \\  /\\  / (_) | | | (_| | | \\__ \\ |_ ");
		System.out.println("  \\____/    \\/  \\/ \\___/|_|  \\__,_|_|_|___/\\__|\n\n\n");


		Scanner in = new Scanner(System.in);

		System.out.print("Press enter to start, type 'h' for help:  ");
		String help = in.nextLine();
		if(help.equalsIgnoreCase("h"))
		{
			System.out.println("Enter the required words, if you want insert more than one, separate them ONLY with commas");
		}
		System.out.println("");
		System.out.println("");



		System.out.print("[+]  Name:  ");
		StringTokenizer name = new StringTokenizer(in.nextLine(), ",");

		while(name.hasMoreTokens())
		{
			wordsAcquisition[index++] = (String) name.nextToken().toLowerCase();
		}
		System.out.println("");



		System.out.print("[+]  Surname:  ");
		StringTokenizer surname = new StringTokenizer(in.nextLine(), ",");

		while(surname.hasMoreTokens())
		{
			wordsAcquisition[index++] = (String) surname.nextToken().toLowerCase();
		}
		System.out.println("");



		System.out.print("[+]  Nickname:  ");
		StringTokenizer nickname = new StringTokenizer(in.nextLine(), ",");

		while(nickname.hasMoreTokens())
		{
			wordsAcquisition[index++] = (String) nickname.nextToken().toLowerCase();
		}
		System.out.println("");



		boolean done_one = false;
		while(!done_one)
		{
			System.out.print("[+]  Birthday (DDMMYYYY):  ");
			StringTokenizer birthDate = new StringTokenizer(in.nextLine(), ",");
			
			try
			{
				while(birthDate.hasMoreTokens())
				{
					String token = (String) birthDate.nextToken();
					wordsAcquisition[index++] = token.substring(0, 2);
					wordsAcquisition[index++] = token.substring(2, 4);
					wordsAcquisition[index++] = token.substring(6);
					wordsAcquisition[index++] = token.substring(4);
				}

				System.out.println("");
				done_one = true;
			}

			catch(IndexOutOfBoundsException e)
			{
				System.out.println("\nWrong format, insert again");
				System.out.println("");
			}
		}



		System.out.print("[+]  Pet name:  ");
		StringTokenizer petName = new StringTokenizer(in.nextLine(), ",");

		while(petName.hasMoreTokens())
		{
			wordsAcquisition[index++] = (String) petName.nextToken().toLowerCase();
		} 
		System.out.println("");


		boolean done_two = false;
		while(!done_two)
		{
			System.out.print("[+]  Important dates(DDMMYYYY):  ");
			StringTokenizer importantDates = new StringTokenizer(in.nextLine(), ",");
		
			try
			{
				while(importantDates.hasMoreTokens())
				{
					String token = (String) importantDates.nextToken();
					wordsAcquisition[index++] = token.substring(0, 2);
					wordsAcquisition[index++] = token.substring(2, 4);
					wordsAcquisition[index++] = token.substring(6);
					wordsAcquisition[index++] = token.substring(4);
				}

				System.out.println("");
				done_two = true;
			}

			catch(IndexOutOfBoundsException e)
			{
				System.out.println("\nWrong format, insert again");
				System.out.println("");
			}
		}


		System.out.print("[+]  Others important words:  ");
		StringTokenizer otherWords = new StringTokenizer(in.nextLine(), ",");

		while(otherWords.hasMoreTokens())
		{
			wordsAcquisition[index++] = (String) otherWords.nextToken().toLowerCase();
		}
		System.out.println("");


		int currentIndex = index;
		for(int i=0; i<currentIndex; i++)
		{
			wordsAcquisition[index++] = wordsAcquisition[i].substring(0, 1).toUpperCase()+wordsAcquisition[i].substring(1);
		}



		wordsAcquisition = resize(wordsAcquisition, index);	


		System.out.print("[+]  Insert special char at the end (y/n) [N]:  ");
		String specialCharChoice = in.nextLine();
		System.out.println("");


		System.out.print("[+]  Output file's name:  ");
		String nameList = in.nextLine();

		
//***********************END DATA ACQUISITION**********************//

		
		System.arraycopy(wordsAcquisition, 0, list, 0, index);
		listIndex = index;

		for(int i=0; i<index; i++)
		{
			if(specialCharChoice.equalsIgnoreCase("y"))
			{
				for(int e=0; e<specialChar.length; e++)
				{
					if(listIndex==list.length-1)
					{
						list = enlarge(list);
					}
					list[listIndex++] = wordsAcquisition[i]+specialChar[e];
				}
			}

			for(int j=0; j<index; j++)
			{
				if(listIndex==list.length-1)
				{
					list = enlarge(list);
				}

				list[listIndex++] = wordsAcquisition[i]+wordsAcquisition[j];

				for(int s=0; s<specialChar.length; s++)
				{
					if(listIndex==list.length-1)
					{
						list = enlarge(list);
					}

					list[listIndex++] = wordsAcquisition[i]+specialChar[s]+wordsAcquisition[j];

					if(specialCharChoice.equalsIgnoreCase("y"))
					{
						for(int e=0; e<specialChar.length; e++)
						{
							if(listIndex==list.length-1)
							{
								list = enlarge(list);
							}
							list[listIndex++] = wordsAcquisition[i]+wordsAcquisition[j]+specialChar[e];

							if(listIndex==list.length-1)
							{
								list = enlarge(list);
							}
							list[listIndex++] = wordsAcquisition[i]+specialChar[s]+wordsAcquisition[j]+specialChar[e];
						}
					}
				}
			}
		}
					

		try
		{
			PrintWriter out = new PrintWriter(nameList+".txt");

			for(int i=0; i<listIndex; i++)
			{
				out.println(list[i]);
			}

			out.close();
		}

		catch(IOException e)
		{
			System.exit(1);
		}

	}

private static String[] enlarge(String[] oldArray)
{
	String[] newArray = new String[oldArray.length*2];
	System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);

	return newArray;
}



private static String[] resize(String[] oldArray, int elements)
{
	String[] newArray = new String[elements];
	System.arraycopy(oldArray, 0, newArray, 0, elements);

	return newArray;
}

	
private static int index = 0;
private static final int ACQUISITION_LENGTH = 1000;
private static String[] wordsAcquisition = new String[ACQUISITION_LENGTH];
private static final int LIST_LENGTH = 100000;
private static String[] list = new String[LIST_LENGTH];
private static int listIndex;
private static final char[] specialChar = {'!', '$', '%', '&', '/', '(', ')', '=', '?', '^', '+', '-', '@', '#', ':', '_', '-', '<', '>'};
}
