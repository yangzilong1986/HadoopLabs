package edu.rosehulman.postcn.Lab1Friends;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FriendList {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: FriendList <input path> <output path>");
			System.exit(-1);
		}
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(FriendList.class);
		job.setJobName("Friend List");
		FileInputFormat.addInputPath(job,  new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(FriendListMapper.class);
		job.setReducerClass(FriendListReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
