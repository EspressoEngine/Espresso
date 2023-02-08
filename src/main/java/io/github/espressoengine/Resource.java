package io.github.espressoengine;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/*
 * Let's say that you are making a game with Maven, and you put your resources in "(root of project)/src/main/resources". You create a new Sound with <code>new Sound("src/main/resources/file.wav")</code>
 * and that works just fine. Then, you decide to compile the program. The file path changed entirely to the root of your .jar!
 * Well, instead of pulling all your hair out AGAIN because of some brainiac's decisions, just replace your path with <code>new Sound(new Resource("file.wav"))</code>!
 */
public class Resource {
    private File file;

	private InputStream stream;

	private URL url;

	public Resource(String path) {
		// Gets a file from a path. If getResource() can't find it, it likely was missing a backslash.
		this.url = this.getClass().getResource(path) == null? this.getClass().getResource("/" + path) : this.getClass().getResource(path);
		this.stream = this.getClass().getResourceAsStream(path) == null? this.getClass().getResourceAsStream("/" + path) : this.getClass().getResourceAsStream(path);
		this.file = new File(this.url.toString());
    }

	public File getFile() {
		return this.file;
	}

	/*
	 * VERY NECESSARY when used inside .jar files. Don't use getFile():
	 * "A Resource in Java is not a File. If the Resource is inside a JAR, for example, you can't access it like a File."
	 * https://stackoverflow.com/questions/22682384/filenotfoundexception-with-getresources
	 */
	public InputStream getStream() {
		return this.stream;
	}

	public String getPath() {
		return this.url.getPath();
	}

	public String toString() {
		return this.url.toString();
	}
}