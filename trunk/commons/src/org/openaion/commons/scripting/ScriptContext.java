package org.openaion.commons.scripting;

import java.io.File;
import java.util.Collection;

import org.openaion.commons.scripting.classlistener.ClassListener;


/**
 * This class represents script context that can be loaded, unloaded, etc...<br>
 */
public interface ScriptContext
{
	/**
	 * Initializes script context. Calls the compilation task.<br>
	 * After compilation static methods marked with {@link org.openaion.commons.scripting.metadata.OnClassLoad} are
	 * invoked
	 */
	public void init();

	/**
	 * Notifies all script classes that they must save their data and release resources to prevent memory leaks. It's
	 * done via static methods with {@link org.openaion.commons.scripting.metadata.OnClassUnload} annotation
	 */
	public void shutdown();

	/**
	 * Invokes {@link #shutdown()}, after that invokes {@link #init()}. Root folder remains the same, but new compiler
	 * and classloader are used.
	 */
	public void reload();

	/**
	 * Returns the root directory for script engine. Only one script engine per root directory is allowed.
	 * 
	 * @return root directory for script engine
	 */
	public File getRoot();

	/**
	 * Returns compilation result of this script context
	 * 
	 * @return compilation result
	 */
	public CompilationResult getCompilationResult();

	/**
	 * Returns true if this script context is loaded
	 * 
	 * @return true if context is initialized
	 */
	public boolean isInitialized();

	/**
	 * Sets files that represents jar files, they will be used as libraries
	 * 
	 * @param files
	 *            that points to jar file, will be used as libraries
	 */
	public void setLibraries(Iterable<File> files);

	/**
	 * Returns list of files that are used as libraries for this script context
	 * 
	 * @return list of libraries
	 */
	public Iterable<File> getLibraries();

	/**
	 * Returns parent script context of this context. Returns null if none.
	 * 
	 * @return parent Script context of this context or null
	 */
	public ScriptContext getParentScriptContext();

	/**
	 * Returns list of child contexts or null if no contextes present
	 * 
	 * @return list of child contexts or null
	 */
	public Collection<ScriptContext> getChildScriptContexts();

	/**
	 * Adds child contexts to this context. If this context is initialized - chiled context will be initialized
	 * immideatly. In other case child context will be just added and initialized when {@link #init()} would be called.
	 * Duplicated child contexts are not allowed, in such case child will be ignored
	 * 
	 * @param context
	 *            child context
	 */
	public void addChildScriptContext(ScriptContext context);

	/**
	 * Sets the class listener for this script context.
	 * 
	 * @param cl
	 *            class listener
	 */
	public void setClassListener(ClassListener cl);

	/**
	 * Returns class listener associated with this ScriptContext.<br>
	 * If it's null - returns parent classListener.<br>
	 * If parent is null and classListener is null - it will set
	 * {@link org.openaion.commons.scripting.classlistener.DefaultClassListener} as class listener and return it
	 * 
	 * @return Associated class listener
	 */
	public ClassListener getClassListener();

	/**
	 * Sets compiler class name for this script context.<br>
	 * Compiler is not inherrited by children.<br>
	 * 
	 * @param className
	 *            compiler class name
	 */
	public void setCompilerClassName(String className);

	/**
	 * Returns compiler class name that will be used for this script context.
	 * 
	 * @return compiler class name that will be used for tis script context
	 */
	public String getCompilerClassName();

	/**
	 * Tests if this ScriptContext is equal to another ScriptContext. Comparation is done by comparing root files and
	 * parent contexts (if there is any parent)
	 * 
	 * @param obj
	 *            object to compare with
	 * @return result of comparation
	 */
	@Override
	public boolean equals(Object obj);

	/**
	 * Returns hashCoded of this ScriptContext. Hashcode is calculated using root file and parent context(if available)
	 * 
	 * @return hashcode
	 */
	@Override
	public int hashCode();

	/**
	 * This method overrides finalization to ensure that active script context will not be collected by GC. If such
	 * situation happens - {@link #shutdown()} is called to ensure that resources were released.
	 * 
	 * @throws Throwable
	 *             if something goes wrong during finalization
	 */
	void finalize() throws Throwable;
}
