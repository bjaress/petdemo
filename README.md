A requested demonstration that I can actually write code.

# Usage

For development, there is a `run.sh` script that can be used (if you
have Bash and Maven) like:

    $ ./run.sh input.csv ZipCode=97215 Type=cat

All it does is use maven to package a Java jar and then pass arguments
through to that jar.


# Thoughts

## Assumptions

* Queries are by exact match only.
* Output is in CSV on standard out.
* Multipart queries are AND-ed together.


## Future Possibilities

The assumption that queries are by exact match is helpful for a
proof-of-concept, but would probably be the first to go if it were
extended to more features.  It would already be useful to group gender
values like `spayed` and `neutered` with `female` and `male`.  If
adoption fees were included, people would probably want to search by
ranges of costs, rather than an exact match on a dollar amount.


## Testing

There's unit testing of the querying logic.  It might be helpful to also
split out the argument parsing into a separate class and unit test that.

For a simple command-line tool like this one, integration tests could be
a script that runs some queries and does a few simple checks.  For
example, `Type=dog` doesn't produce any cats or any errors.  I don't
like using exact input/output file pairs for integration because I think
the more code a test covers the more strategic it needs to be about its
assertions.


## Tools

Version control is definitely a net win.  Modern systems help a lot with
combining work from different people.  Even older systems are valuable
for tracking the state and history of the code.  The main annoyance is
each system has its own way of thinking and talking about even basic
tasks, so it can be painful to switch between them.
