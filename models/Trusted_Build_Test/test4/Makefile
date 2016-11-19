src = $(wildcard *.c)
obj = $(src:.c=.o)

LDFLAGS = -pthreads 

myprog: $(obj)
	$(CC) -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(obj) myprog
