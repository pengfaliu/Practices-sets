import json

result = {}
def jsonoutput(filename):
    new = {}
    file_object = open(filename)
    try:
        all_the_text = file_object.readlines()
        for i in all_the_text:
            new['id'] = i.split('/')[-1].rstrip()
            new['url'] = i.rstrip()
            result[new['id']] = new 
            new = {} 
        final_json_ld = {}
        final_json_ld['@context'] = result
        return json.dumps(final_json_ld,indent=3,ensure_ascii=False)
    finally:
        file_object.close()


if __name__ == '__main__':
    filename = 'final.txt'
    print jsonoutput(filename)