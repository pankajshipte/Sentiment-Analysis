package com.vrishank.SentAnalysis.Classifier;

/*This is the main class where feature vectors are created
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public class TestClassifier
{
	public static TreeMap<String, Integer> support_Vector = new TreeMap<String, Integer>();
	
	public static void readSupportVectorFromFile(String model_FileNamewithPath) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(model_FileNamewithPath));
		String line = null;
		while((line=br.readLine())!=null){
			String []strarr = line.split("\t");
			support_Vector.put(strarr[0], Integer.parseInt(strarr[1]));
		}
		br.close();
		System.out.println("SV SIZE: "+support_Vector.size()+"  model_FileNamewithPath  "+model_FileNamewithPath);
		System.out.println(support_Vector.get("EN_O") +"    "+ support_Vector.get("EN_#"));
	}

	public static void main(String[] args) throws Exception
	{
		/*
		 * Args:
		 * 0  Input test File Name with Path
		 * 1  Output test vector File Name with Path
		 * 2  Support vector model file Name with full path
		 * 3  English Sentiword Net file name (full path)
		 * 4  Hindi Sentiword Net file name (full path)
		 * 5  English POS tags counts file name with full path
		 * 6  Hindi POS tags counts file name with full path
		 * 7  PMI Dictionary with full path
		 * 8  Normalized Negation handled Input Test
		 */

		if(args.length != 9){
			System.out.println("ERROR... Usage: java ipFileNameWithPath opFileNameWithPath " +
					"model_FileNamewithPath englishSentiWordNet hindiSentiWordNet " +
					"englishPOSTagFileName, hindiPOSTagFileName, PMIDictFile, norm_neg_input_File");
			System.exit(0);
		}
		
		String ipFileNameWithPath = args[0];
		String opFileNameWithPath = args[1];
		String model_FileNamewithPath = args[2];
		String englishSentiWordNet = args[3];
		String hindiSentiWordNet = args[4];
		String englishPOSTagFileName = args[5];
		String hindiPOSTagFileName = args[6];
		String PMIDictFile = args[7];
		String norm_neg_input_File = args[8];

		try
		{
			readSupportVectorFromFile(model_FileNamewithPath);
			System.out.println("Vector SIZE: "+support_Vector.size());
			System.out.println("Creating Test Feature Vector..");
			FeatureVector.createFeatureVector(ipFileNameWithPath, opFileNameWithPath, 
					norm_neg_input_File, support_Vector, englishSentiWordNet, hindiSentiWordNet, 
					englishPOSTagFileName, hindiPOSTagFileName, PMIDictFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
