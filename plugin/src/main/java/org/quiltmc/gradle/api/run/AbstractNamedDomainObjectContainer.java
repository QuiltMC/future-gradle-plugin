package org.quiltmc.gradle.api.run;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.api.DomainObjectCollection;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.NamedDomainObjectCollectionSchema;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.NamedDomainObjectProvider;
import org.gradle.api.NamedDomainObjectSet;
import org.gradle.api.Namer;
import org.gradle.api.Rule;
import org.gradle.api.UnknownDomainObjectException;
import org.gradle.api.provider.Provider;
import org.gradle.api.specs.Spec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class AbstractNamedDomainObjectContainer<T> implements NamedDomainObjectContainer<T> {
	protected final NamedDomainObjectContainer<T> delegate;

	AbstractNamedDomainObjectContainer(NamedDomainObjectContainer<T> delegate) {
		this.delegate = delegate;
	}

	@Override
	public T create(String s) throws InvalidUserDataException {
		return this.delegate.create(s);
	}

	@Override
	public T maybeCreate(String s) {
		return this.delegate.maybeCreate(s);
	}

	@Override
	public T create(String s, Closure closure) throws InvalidUserDataException {
		return this.delegate.create(s, closure);
	}

	@Override
	public T create(String s, Action<? super T> action) throws InvalidUserDataException {
		return this.delegate.create(s, action);
	}

	@Override
	public NamedDomainObjectContainer<T> configure(Closure closure) {
		return this.delegate.configure(closure);
	}

	@Override
	public NamedDomainObjectProvider<T> register(String s, Action<? super T> action) throws InvalidUserDataException {
		return this.delegate.register(s, action);
	}

	@Override
	public NamedDomainObjectProvider<T> register(String s) throws InvalidUserDataException {
		return this.delegate.register(s);
	}

	@Override
	public int size() {
		return this.delegate.size();
	}

	@Override
	public boolean isEmpty() {
		return this.delegate.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.delegate.contains(o);
	}

	@NotNull
	@Override
	public Iterator<T> iterator() {
		return this.delegate.iterator();
	}

	@NotNull
	@Override
	public Object[] toArray() {
		return this.delegate.toArray();
	}

	@NotNull
	@Override
	public <T> T[] toArray(T[] a) {
		return this.delegate.toArray(a);
	}

	@Override
	public boolean add(T T) {
		return this.delegate.add(T);
	}

	@Override
	public boolean remove(Object o) {
		return this.delegate.remove(o);
	}

	@Override
	public boolean containsAll(@NotNull Collection<?> c) {
		return this.delegate.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		return this.delegate.addAll(collection);
	}

	@Override
	public boolean removeAll(@NotNull Collection<?> c) {
		return this.delegate.removeAll(c);
	}

	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		return this.delegate.retainAll(c);
	}

	@Override
	public void clear() {
		this.delegate.clear();
	}

	@Override
	public Namer<T> getNamer() {
		return this.delegate.getNamer();
	}

	@Override
	public SortedMap<String, T> getAsMap() {
		return this.delegate.getAsMap();
	}

	@Override
	public SortedSet<String> getNames() {
		return this.delegate.getNames();
	}

	@Nullable
	@Override
	public T findByName(String name) {
		return this.delegate.findByName(name);
	}

	@Override
	public T getByName(String name) throws UnknownDomainObjectException {
		return this.delegate.getByName(name);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public T getByName(String name, Closure configureClosure) throws UnknownDomainObjectException {
		return this.delegate.getByName(name, configureClosure);
	}

	@Override
	public T getByName(String name, Action<? super T> configureAction) throws UnknownDomainObjectException {
		return this.delegate.getByName(name, configureAction);
	}

	@Override
	public T getAt(String name) throws UnknownDomainObjectException {
		return this.delegate.getAt(name);
	}

	@Override
	public Rule addRule(Rule rule) {
		return this.delegate.addRule(rule);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Rule addRule(String description, Closure closure) {
		return this.delegate.addRule(description, closure);
	}

	@Override
	public Rule addRule(String description, Action<String> action) {
		return this.delegate.addRule(description, action);
	}

	@Override
	public List<Rule> getRules() {
		return this.delegate.getRules();
	}

	@Override
	public void addLater(Provider<? extends T> provider) {
		this.delegate.addLater(provider);
	}

	@Override
	public void addAllLater(Provider<? extends Iterable<T>> provider) {
		this.delegate.addAllLater(provider);
	}

	@Override
	public <S extends T> NamedDomainObjectSet<S> withType(Class<S> type) {
		return this.delegate.withType(type);
	}

	@Override
	public <S extends T> DomainObjectCollection<S> withType(Class<S> type, Action<? super S> action) {
		return this.delegate.withType(type, action);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public <S extends T> DomainObjectCollection<S> withType(Class<S> type, Closure closure) {
		return this.delegate.withType(type, closure);
	}

	@Override
	public NamedDomainObjectSet<T> matching(Spec<? super T> spec) {
		return this.delegate.matching(spec);
	}

	@Override
	public NamedDomainObjectSet<T> matching(Closure closure) {
		return this.delegate.matching(closure);
	}

	@Override
	public Action<? super T> whenObjectAdded(Action<? super T> action) {
		return this.delegate.whenObjectAdded(action);
	}

	@Override
	public void whenObjectAdded(Closure closure) {
		this.delegate.whenObjectAdded(closure);
	}

	@Override
	public Action<? super T> whenObjectRemoved(Action<? super T> action) {
		return this.delegate.whenObjectRemoved(action);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void whenObjectRemoved(Closure action) {
		this.delegate.whenObjectRemoved(action);
	}

	@Override
	public void all(Action<? super T> action) {
		this.delegate.all(action);
	}

	@Override
	public void all(Closure closure) {
		this.delegate.all(closure);
	}

	@Override
	public void configureEach(Action<? super T> action) {
		this.delegate.configureEach(action);
	}

	@Override
	public NamedDomainObjectProvider<T> named(String name) throws UnknownDomainObjectException {
		return this.delegate.named(name);
	}

	@Override
	public NamedDomainObjectProvider<T> named(String name, Action<? super T> configurationAction) throws UnknownDomainObjectException {
		return this.delegate.named(name, configurationAction);
	}

	@Override
	public <S extends T> NamedDomainObjectProvider<S> named(String name, Class<S> type) throws UnknownDomainObjectException {
		return this.delegate.named(name, type);
	}

	@Override
	public <S extends T> NamedDomainObjectProvider<S> named(String name, Class<S> type, Action<? super S> configurationAction) throws UnknownDomainObjectException {
		return this.delegate.named(name, type, configurationAction);
	}

	@Override
	public NamedDomainObjectCollectionSchema getCollectionSchema() {
		return this.delegate.getCollectionSchema();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Set<T> findAll(Closure closure) {
		return this.delegate.findAll(closure);
	}
}
