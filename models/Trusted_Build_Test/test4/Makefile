src = $(wildcard *.c)
obj = $(src:.c=.o)

LDFLAGS =  

myprog: $(obj)
	$(CC) -pthread -Wall -Werror -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(obj) myprog		
